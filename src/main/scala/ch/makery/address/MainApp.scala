package ch.makery.address
import ch.makery.address.model.Person
import ch.makery.address.util.Database
import ch.makery.address.view.{PersonEditDialogController}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Scene, control}
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{beans, scene => jfxs}
import scalafx.beans.{Observable, value}
import scalafx.collections.ObservableBuffer
import scalafx.stage.{Modality, Stage}

import scala.math.Equiv.by

object MainApp extends JFXApp {
  //Initialize Database
  Database.setupDB()

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

  /**val personData = new ObservableBuffer[Person]()
  personData += new Person("Yuan", "Jing")
  personData += new Person("Liu", "Jing")
**/

  val personData = new ObservableBuffer[Person]()
  personData ++= Person.getAllPersons


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

  def showPersonEditDialog(person: Person): Boolean={
    val resource = getClass.getResourceAsStream("view/PersonEditDialog.fxml")
    val loader = new FXMLLoader(fxml = null, NoDependencyResolver)
    loader.load(resource)
    val roots2 = loader.getRoot[javafx.scene.Parent]
    val control = loader.getController[PersonEditDialogController#Controller]

    val dialog = new Stage(){
      initModality(Modality.ApplicationModal)
      initOwner(stage)
      scene = new Scene{
        root = roots2
      }
    }
    control.dialogStage = dialog
    control.person = person
    dialog.showAndWait()
    control.okClicked
  }

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
