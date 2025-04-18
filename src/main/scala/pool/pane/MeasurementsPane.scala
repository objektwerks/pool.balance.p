package pool.pane

import scalafx.Includes.*
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, SelectionMode, TableColumn, TableView}
import scalafx.scene.layout.{HBox, Priority, VBox}

import scala.util.control.NonFatal

import pool.{Measurement, Context, Model}
import pool.dialog.{MeasurementDialog, MeasurementsChartDialog}

final class MeasurementsPane(context: Context, model: Model) extends VBox:
  spacing = 6
  padding = Insets(6)

  val tableView = new TableView[Measurement]():
    columns ++= List(
      new TableColumn[Measurement, Int]:
        text = context.headerTotalChlorine
        cellValueFactory = _.value.totalChlorineProperty
      ,
      new TableColumn[Measurement, Int]:
        text = context.headerFreeChlorine
        cellValueFactory = _.value.freeChlorineProperty
      ,
      new TableColumn[Measurement, Double]:
        text = context.headerCombinedChlorine
        cellValueFactory = _.value.combinedChlorineProperty
      ,
      new TableColumn[Measurement, Double]:
        prefWidth = 50
        text = context.headerPh
        cellValueFactory = _.value.phProperty
      ,
      new TableColumn[Measurement, Int]:
        text = context.headerCalciumHardness
        cellValueFactory = _.value.calciumHardnessProperty
      ,
      new TableColumn[Measurement, Int]:
        text = context.headerTotalAlkalinity
        cellValueFactory = _.value.totalAlkalinityProperty
      ,
      new TableColumn[Measurement, Int]:
        text = context.headerCyanuricAcid
        cellValueFactory = _.value.cyanuricAcidProperty
      ,
      new TableColumn[Measurement, Int]:
        text = context.headerTotalBromine
        cellValueFactory = _.value.totalBromineProperty
      ,
      new TableColumn[Measurement, Int]:
        prefWidth = 50
        text = context.headerSalt
        cellValueFactory = _.value.saltProperty
      ,
      new TableColumn[Measurement, Int]:
        prefWidth = 50
        text = context.headerTemperature
        cellValueFactory = _.value.temperatureProperty
      ,
      new TableColumn[Measurement, String]:
        text = context.headerMeasured
        cellValueFactory = _.value.measuredProperty
    )
    items = model.observableMeasurements

  val addButton = new Button:
    graphic = context.addImage
    text = context.buttonAdd
    disable = true
    onAction = { _ => add() }

  val editButton = new Button:
    graphic = context.editImage
    text = context.buttonEdit
    disable = true
    onAction = { _ => update() }

  val chartButton = new Button:
    graphic = context.chartImage
    text = context.buttonChart
    disable = true
    onAction = { _ => chart() }

  val buttonBar = new HBox:
    spacing = 6
    children = List(addButton, editButton, chartButton)

  model.selectedPoolId.onChange { (_, _, _) =>
    addButton.disable = false
    chartButton.disable = false
  }

  children = List(tableView, buttonBar)
  VBox.setVgrow(tableView, Priority.Always)

  tableView.onMouseClicked = { event =>
    if (event.getClickCount == 2 && tableView.selectionModel().getSelectedItem != null) then update()
  }

  tableView.selectionModel().selectionModeProperty.value = SelectionMode.Single

  tableView.selectionModel().selectedItemProperty().addListener { (_, _, selectedItem) =>
    if selectedItem != null then
      model.selectedMeasurementId.value = selectedItem.id
      editButton.disable = false
  }

  def add(): Unit =
    MeasurementDialog(context, Measurement(poolId = model.selectedPoolId.value)).showAndWait() match
      case Some(measurement: Measurement) =>
        try
          tableView.selectionModel().select( model.add(measurement) )
        catch case NonFatal(error) => model.onError(error, s"Add measurement failed: ${error.getMessage}")
      case _ =>

  def update(): Unit =
    val selectedIndex = tableView.selectionModel().getSelectedIndex
    val measurement = tableView.selectionModel().getSelectedItem.measurement
    MeasurementDialog(context, measurement).showAndWait() match
      case Some(measurement: Measurement) =>
        try
          model.update(selectedIndex, measurement)
          tableView.selectionModel().select(selectedIndex)
        catch case NonFatal(error) => model.onError(error, s"Update measurement failed: ${error.getMessage}")
      case _ =>

  def chart(): Unit = MeasurementsChartDialog(context, model).showAndWait()