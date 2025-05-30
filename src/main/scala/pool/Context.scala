package pool

import com.typesafe.config.Config

import scalafx.scene.image.{Image, ImageView}

final class Context(config: Config):
  val url = config.getString("db.url")
  val user = config.getString("db.user")
  val password = config.getString("db.password")
  val dataSourceClassName = config.getString("db.dataSourceClassName")
  val maximumPoolSize = config.getInt("db.maximumPoolSize")

  val windowTitle = config.getString("window.title")
  val windowWidth = config.getDouble("window.width")
  val windowHeight = config.getDouble("window.height")

  val aboutAlertHeaderText = config.getString("about.alert.headerText")
  val aboutAlertContentText = config.getString("about.alert.contentText")

  val menuMenu = config.getString("menu.menu")
  val menuAbout = config.getString("menu.about")
  val menuExit = config.getString("menu.exit")

  val buttonAdd = config.getString("button.add")
  val buttonEdit = config.getString("button.edit")
  val buttonSave = config.getString("button.save")
  val buttonChart = config.getString("button.chart")
  val buttonErrors = config.getString("button.errors")
  val buttonConverter = config.getString("button.converter")

  val chartCleanings = config.getString("chart.cleanings")
  val chartChemicals = config.getString("chart.chemicals")
  val chartMeasurements = config.getString("chart.measurements")
  val chartMonthDay = config.getString("chart.monthDay")
  val chartMin = config.getString("chart.min")
  val chartMax = config.getString("chart.max")
  val chartAvg = config.getString("chart.avg")
  val chartYCleanings = config.getString("chart.yCleanings")
  val chartTotalChlorine = config.getString("chart.totalChlorine")
  val chartFreeChlorine = config.getString("chart.freeChlorine")
  val chartCombinedChlorine = config.getString("chart.combinedChlorine")
  val chartPh = config.getString("chart.ph")
  val chartCalciumHardness = config.getString("chart.calciumHardness")
  val chartTotalAlkalinity = config.getString("chart.totalAlkalinity")
  val chartCyanuricAcid = config.getString("chart.cyanuricAcid")
  val chartTotalBromine = config.getString("chart.totalBromine")
  val chartSalt = config.getString("chart.salt")
  val chartLiquidChlorine = config.getString("chart.liquidChlorine")
  val chartTrichlor = config.getString("chart.trichlor")
  val chartDichlor = config.getString("chart.dichlor")
  val chartCalciumHypochlorite = config.getString("chart.calciumHypochlorite")
  val chartStabilizer = config.getString("chart.stabilizer")
  val chartAlgaecide = config.getString("chart.algaecide")
  val chartMuriaticAcid = config.getString("chart.muriaticAcid")
  val chartGranularSalt = config.getString("chart.granularSalt")

  val columnYes = config.getString("column.yes")
  val columnNo = config.getString("column.no")

  val converterGallons = config.getString("converter.gallons")
  val converterLiters = config.getString("converter.liters")
  val converterPounds = config.getString("converter.pounds")
  val converterKilograms = config.getString("converter.kilograms")

  val dashboardTotalChlorineRange = config.getString("dashboard.totalChlorineRange")
  val dashboardTotalChlorineGood = config.getString("dashboard.totalChlorineGood")
  val dashboardTotalChlorineIdeal = config.getString("dashboard.totalChlorineIdeal")
  val dashboardFreeChlorineRange = config.getString("dashboard.freeChlorineRange")
  val dashboardFreeChlorineGood = config.getString("dashboard.freeChlorineGood")
  val dashboardFreeChlorineIdeal = config.getString("dashboard.freeChlorineIdeal")
  val dashboardCombinedChlorineRange = config.getString("dashboard.combinedChlorineRange")
  val dashboardCombinedChlorineGood = config.getString("dashboard.combinedChlorineGood")
  val dashboardCombinedChlorineIdeal = config.getString("dashboard.combinedChlorineIdeal")
  val dashboardPhRange = config.getString("dashboard.phRange")
  val dashboardPhGood = config.getString("dashboard.phGood")
  val dashboardPhIdeal = config.getString("dashboard.phIdeal")
  val dashboardCalciumHardnessRange = config.getString("dashboard.calciumHardnessRange")
  val dashboardCalciumHardnessGood = config.getString("dashboard.calciumHardnessGood")
  val dashboardCalciumHardnessIdeal = config.getString("dashboard.calciumHardnessIdeal")
  val dashboardTotalAlkalinityRange = config.getString("dashboard.totalAlkalinityRange")
  val dashboardTotalAlkalinityGood = config.getString("dashboard.totalAlkalinityGood")
  val dashboardTotalAlkalinityIdeal = config.getString("dashboard.totalAlkalinityIdeal")
  val dashboardCyanuricAcidRange = config.getString("dashboard.cyanuricAcidRange")
  val dashboardCyanuricAcidGood = config.getString("dashboard.cyanuricAcidGood")
  val dashboardCyanuricAcidIdeal = config.getString("dashboard.cyanuricAcidIdeal")
  val dashboardTotalBromineRange = config.getString("dashboard.totalBromineRange")
  val dashboardTotalBromineGood = config.getString("dashboard.totalBromineGood")
  val dashboardTotalBromineIdeal = config.getString("dashboard.totalBromineIdeal")
  val dashboardSaltRange = config.getString("dashboard.saltRange")
  val dashboardSaltGood = config.getString("dashboard.saltGood")
  val dashboardSaltIdeal = config.getString("dashboard.saltIdeal")
  val dashboardTemperatureRange = config.getString("dashboard.temperatureRange")
  val dashboardTemperatureGood = config.getString("dashboard.temperatureGood")
  val dashboardTemperatureIdeal = config.getString("dashboard.temperatureIdeal")

  val dialogPool = config.getString("dialog.pool")
  val dialogCleaning = config.getString("dialog.cleaning")
  val dialogMeasurement = config.getString("dialog.measurement")
  val dialogChemical = config.getString("dialog.chemical")
  val dialogErrors = config.getString("dialog.errors")

  val headerName = config.getString("header.name")
  val headerBuilt = config.getString("header.built")
  val headerVolume = config.getString("header.volume")
  val headerUnit = config.getString("header.unit")
  val headerBrush = config.getString("header.brush")
  val headerNet = config.getString("header.net")
  val headerSkimmerBasket = config.getString("header.skimmerBasket")
  val headerPumpBasket = config.getString("header.pumpBasket")
  val headerPumpFilter = config.getString("header.pumpFilter")
  val headerVacuum = config.getString("header.vacuum")
  val headerCleaned = config.getString("header.cleaned")
  val headerTotalChlorine = config.getString("header.totalChlorine")
  val headerFreeChlorine = config.getString("header.freeChlorine")
  val headerCombinedChlorine = config.getString("header.combinedChlorine")
  val headerPh = config.getString("header.ph")
  val headerCalciumHardness = config.getString("header.calciumHardness")
  val headerTotalAlkalinity = config.getString("header.totalAlkalinity")
  val headerCyanuricAcid = config.getString("header.cyanuricAcid")
  val headerTotalBromine = config.getString("header.totalBromine")
  val headerSalt = config.getString("header.salt")
  val headerTemperature = config.getString("header.temperature")
  val headerMeasured = config.getString("header.measured")
  val headerTypeof = config.getString("header.typeof")
  val headerAmount = config.getString("header.amount")
  val headerAdded = config.getString("header.added")
  val headerOccurred = config.getString("header.occurred")
  val headerError = config.getString("header.error")

  val labelPools = config.getString("label.pools")
  val labelCleanings = config.getString("label.cleanings")
  val labelMeasurements = config.getString("label.measurements")
  val labelChemicals = config.getString("label.chemicals")
  val labelName = config.getString("label.name")
  val labelBuilt = config.getString("label.built")
  val labelVolume = config.getString("label.volume")
  val labelUnit = config.getString("label.unit")
  val labelBrush = config.getString("label.brush")
  val labelNet = config.getString("label.net")
  val labelSkimmerBasket = config.getString("label.skimmerBasket")
  val labelPumpBasket = config.getString("label.pumpBasket")
  val labelPumpFilter = config.getString("label.pumpFilter")
  val labelVacuum = config.getString("label.vacuum")
  val labelCleaned = config.getString("label.cleaned")
  val labelTotalChlorine = config.getString("label.totalChlorine")
  val labelFreeChlorine = config.getString("label.freeChlorine")
  val labelCombinedChlorine = config.getString("label.combinedChlorine")
  val labelPh = config.getString("label.ph")
  val labelCalciumHardness = config.getString("label.calciumHardness")
  val labelTotalAlkalinity = config.getString("label.totalAlkalinity")
  val labelCyanuricAcid = config.getString("label.cyanuricAcid")
  val labelTotalBromine = config.getString("label.totalBromine")
  val labelSalt = config.getString("label.salt")
  val labelTemperature = config.getString("label.temperature")
  val labelMeasure = config.getString("label.measured")
  val labelTypeof = config.getString("label.typeof")
  val labelAmount = config.getString("label.amount")
  val labelAdded = config.getString("label.added")
  val labelRange = config.getString("label.range")
  val labelGood = config.getString("label.good")
  val labelIdeal = config.getString("label.ideal")
  val labelCurrent = config.getString("label.current")
  val labelAverage = config.getString("label.average")

  val tabCleanings = config.getString("tab.cleanings")
  val tabMeasurements = config.getString("tab.measurements")
  val tabChemicals = config.getString("tab.chemicals")

  def appIcon = new Image(Image.getClass.getResourceAsStream("/image/icon.png"))

  def addImage = loadImageView("/image/add.png")
  def editImage = loadImageView("/image/edit.png")
  def chartImage = loadImageView("/image/chart.png")
  def errorsImage = loadImageView("/image/errors.png")

  private def loadImageView(path: String): ImageView = new ImageView:
    image = new Image(Image.getClass.getResourceAsStream(path))
    fitHeight = 25
    fitWidth = 25
    preserveRatio = true
    smooth = true