package ch.makery.address
import ch.makery.address.model.Person
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{beans, scene => jfxs}
import scalafx.beans.{Observable, value}
import scalafx.collections.ObservableBuffer

import scala.math.Equiv.by

object MainApp extends JFXApp {
  // transform path of RootLayout.fxml to URI for resource location.
  val rootResource = getClass.getResource("view/RootLayout.fxml")
  // initialize the loader object.
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  // Load root layout from fxml file.
  loader.load();
  // retrieve the root component BorderPane from the FXML
  val roots = loader.getRoot[jfxs.layout.BorderPane]
  // initialize stage
  stage = new PrimaryStage {
    title = "AddressApp"
    scene = new Scene {
      root = roots
    }
  }

  val personData = new ObservableBuffer[Person]()
  personData += new Person("Yuan", "Jing")
  personData += new Person("Liu", "Jing")


  // actions for display person overview window
  def showPersonOverview() = {
    val resource = getClass.getResource("view/PersonOverview.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }
  // call to display PersonOverview when app start
  showPersonOverview()

  var a:Option[Int] = None
  println(a.isDefined)
  a = Some(3)
  println(a.get)

  //implicit parameters

  def multiply(value:Int)(implicit by:Int) ={
    value * by
  }
  implicit val multiplier = 2
  println(multiply(10))
  println(multiply(10)(7))

  //anonymous function
  def add (a:Int, b:Int) = {a + b}
  val add1 = (a:Int, b:Int) => {a + b}
  println(add1(10,54))
}
