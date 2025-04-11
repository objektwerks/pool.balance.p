package pool

import com.typesafe.scalalogging.LazyLogging

import java.text.NumberFormat

import ox.*

import scalafx.application.Platform
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.ObjectProperty

import Entity.given
import Measurement.*

final class Model(store: Store) extends LazyLogging:
  def assertNotInFxThread(): Unit =
    assert( !Platform.isFxApplicationThread, "Model / Store operation should not be called on Fx thread!" )

  val selectedPoolId = ObjectProperty[Long](0)
  val selectedCleaningId = ObjectProperty[Long](0)
  val selectedMeasurementId = ObjectProperty[Long](0)
  val selectedChemicalId = ObjectProperty[Long](0)

  selectedPoolId.onChange { (_, oldPoolId, newPoolId) =>
    supervised:
      assertNotInFxThread()
      logger.info(s"selected oool id onchange event: $oldPoolId -> $newPoolId")
      cleanings(newPoolId)
      measurements(newPoolId)
      chemicals(newPoolId)
  }

  val observableErrors = ObservableBuffer[Error]()
  val observablePools = ObservableBuffer[Pool]()
  val observableCleanings = ObservableBuffer[Cleaning]()
  val observableMeasurements = ObservableBuffer[Measurement]()
  val observableChemicals = ObservableBuffer[Chemical]()

  observableMeasurements.onChange { (_, _) =>
    assertNotInFxThread()
    logger.info(s"observable measurements onchange event.")
    Platform.runLater( dashboard() )
  }

  val currentTotalChlorine = ObjectProperty[Int](0)
  val averageTotalChlorine = ObjectProperty[Int](0)
  def totalChlorineInRange(value: Int): Boolean = totalChlorineRange.contains(value)

  val currentFreeChlorine = ObjectProperty[Int](0)
  val averageFreeChlorine = ObjectProperty[Int](0)
  def freeChlorineInRange(value: Int): Boolean = freeChlorineRange.contains(value)

  val currentCombinedChlorine = ObjectProperty[Double](0)
  val averageCombinedChlorine = ObjectProperty[Double](0)
  def combinedChlorineInRange(value: Double): Boolean = combinedChlorineRange.contains(value)

  val currentPh = ObjectProperty[Double](0)
  val averagePh = ObjectProperty[Double](0)
  def phInRange(value: Double): Boolean = phRange.contains(value)

  val currentCalciumHardness = ObjectProperty[Int](0)
  val averageCalciumHardness = ObjectProperty[Int](0)
  def calciumHardnessInRange(value: Int): Boolean = calciumHardnessRange.contains(value)

  val currentTotalAlkalinity = ObjectProperty[Int](0)
  val averageTotalAlkalinity = ObjectProperty[Int](0)
  def totalAlkalinityInRange(value: Int): Boolean = totalAlkalinityRange.contains(value)

  val currentCyanuricAcid = ObjectProperty[Int](0)
  val averageCyanuricAcid = ObjectProperty[Int](0)
  def cyanuricAcidInRange(value: Int): Boolean = cyanuricAcidRange.contains(value)

  val currentTotalBromine = ObjectProperty[Int](0)
  val averageTotalBromine = ObjectProperty[Int](0)
  def totalBromineInRange(value: Int): Boolean = totalBromineRange.contains(value)

  val currentSalt = ObjectProperty[Int](0)
  val averageSalt = ObjectProperty[Int](0)
  def saltInRange(value: Int): Boolean = saltRange.contains(value)

  val currentTemperature = ObjectProperty[Int](0)
  val averageTemperature = ObjectProperty[Int](0)
  def temperatureInRange(value: Int): Boolean = temperatureRange.contains(value)

  pools()

  def pools(): Unit =
    supervised:
      assertNotInFxThread()
      observablePools ++= store.pools()

  def cleanings(poolId: Long): Unit =
    supervised:
      assertNotInFxThread()
      observableCleanings.clear()
      observableCleanings ++= store.cleanings(poolId)

  def measurements(poolId: Long): Unit =
    supervised:
      assertNotInFxThread()
      observableMeasurements.clear()
      observableMeasurements ++= store.measurements(poolId) 

  def chemicals(poolId: Long): Unit =
    supervised:
      assertNotInFxThread()
      observableChemicals.clear()
      observableChemicals ++= store.chemicals(poolId) 

  def add(pool: Pool): Pool =
    supervised:
      assertNotInFxThread()
      val newPool = store.add(pool)
      observablePools += newPool
      observablePools.sort()
      selectedPoolId.value = newPool.id
      newPool

  def update(selectedIndex: Int, pool: Pool): Unit =
    supervised:
      assertNotInFxThread()
      store.update(pool)
      observablePools.update(selectedIndex, pool)
      observablePools.sort()
      selectedPoolId.value = pool.id

  def add(cleaning: Cleaning): Cleaning =
    supervised:
      assertNotInFxThread()
      val newCleaning = store.add(cleaning)
      observableCleanings += newCleaning
      observableCleanings.sort()
      selectedCleaningId.value = newCleaning.id
      newCleaning

  def update(selectedIndex: Int, cleaning: Cleaning): Unit =
    supervised:
      assertNotInFxThread()
      store.update(cleaning)
      observableCleanings.update(selectedIndex, cleaning)
      observableCleanings.sort()
      selectedCleaningId.value = cleaning.id
  
  def add(measurement: Measurement): Measurement =
    supervised:
      assertNotInFxThread()
      val newMeasurement = store.add(measurement)
      observableMeasurements += newMeasurement
      observableMeasurements.sort()
      selectedMeasurementId.value = newMeasurement.id
      newMeasurement

  def update(selectedIndex: Int, measurement: Measurement): Unit =
    supervised:
      assertNotInFxThread()
      store.update(measurement)
      observableMeasurements.update(selectedIndex, measurement)
      observableMeasurements.sort()
      selectedMeasurementId.value = measurement.id
  
  def add(chemical: Chemical): Chemical =
    supervised:
      assertNotInFxThread()
      val newChemical = store.add(chemical)
      observableChemicals += newChemical
      observableChemicals.sort()
      selectedChemicalId.value = newChemical.id      
      newChemical

  def update(selectedIndex: Int, chemical: Chemical): Unit =
    supervised:
      assertNotInFxThread()
      store.update(chemical)
      observableChemicals.update(selectedIndex, chemical)
      observableChemicals.sort()
      selectedChemicalId.value = chemical.id

  def onError(message: String): Unit =
    observableErrors += Error(message)
    logger.error(message)

  def onError(error: Throwable, message: String): Unit =
    observableErrors += Error(message)
    logger.error(message, error)

  private def dashboard(): Unit =
    val numberFormat = NumberFormat.getNumberInstance()
    numberFormat.setMaximumFractionDigits(1)
    observableMeasurements.headOption.foreach { measurement =>
      onCurrent(measurement, numberFormat)
      onAverage(numberFormat)
    }

  private def onCurrent(measurement: Measurement, numberFormat: NumberFormat): Unit =
    currentTotalChlorine.value = measurement.totalChlorine
    currentFreeChlorine.value = measurement.freeChlorine
    currentCombinedChlorine.value = numberFormat.format( measurement.combinedChlorine ).toDouble
    currentPh.value = numberFormat.format( measurement.ph ).toDouble
    currentCalciumHardness.value = measurement.calciumHardness
    currentTotalAlkalinity.value = measurement.totalAlkalinity
    currentCyanuricAcid.value = measurement.cyanuricAcid
    currentTotalBromine.value = measurement.totalBromine
    currentSalt.value = measurement.salt
    currentTemperature.value = measurement.temperature

  private def onAverage(numberFormat: NumberFormat): Unit =
    val count = observableMeasurements.length
    averageTotalChlorine.value = observableMeasurements.map(_.totalChlorine).sum / count
    averageFreeChlorine.value = observableMeasurements.map(_.freeChlorine).sum / count
    averageCombinedChlorine.value = numberFormat.format( observableMeasurements.map(_.combinedChlorine).sum / count ).toDouble
    averagePh.value = numberFormat.format( observableMeasurements.map(_.ph).sum / count ).toDouble
    averageCalciumHardness.value = observableMeasurements.map(_.calciumHardness).sum / count
    averageTotalAlkalinity.value = observableMeasurements.map(_.totalAlkalinity).sum / count
    averageCyanuricAcid.value = observableMeasurements.map(_.cyanuricAcid).sum / count
    averageTotalBromine.value = observableMeasurements.map(_.totalBromine).sum / count
    averageSalt.value = observableMeasurements.map(_.salt).sum / count
    averageTemperature.value = observableMeasurements.map(_.temperature).sum / count