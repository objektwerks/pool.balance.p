package pool

import scalafx.geometry.Orientation
import scalafx.scene.Scene
import scalafx.scene.control.SplitPane
import scalafx.scene.layout.{HBox, Priority, VBox}

import pool.dashboard.DashboardPane
import pool.menu.Menu
import pool.pane.{PoolsPane, TabbedPane}

final class View(context: Context, model: Model):
  val menu = Menu(context)

  val dashboardPane = DashboardPane(context, model)
  HBox.setHgrow(dashboardPane, Priority.Always)

  val poolsPane = PoolsPane(context, model)
  VBox.setVgrow(poolsPane, Priority.Always)

  val tabbedPane = TabbedPane(context, model)
  VBox.setVgrow(tabbedPane, Priority.Always)

  val splitPane = new SplitPane {
    orientation = Orientation.Horizontal
    items ++= List(poolsPane, tabbedPane)
  }
  splitPane.setDividerPositions(0.25, 0.75)
  VBox.setVgrow(splitPane, Priority.Always)

  val vbox = new VBox:
    children = List(menu, dashboardPane, splitPane)

  val scene = new Scene:
    root = vbox
    stylesheets = List("/style.css")