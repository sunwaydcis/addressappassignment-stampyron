package ch.makery.address.view
import ch.makery.address.MainApp
import ch.makery.address.model.Person
import scalafx.scene.control.{Alert, Label, TableColumn, TableView}
import scalafxml.core.macros.sfxml

import scala.reflect.internal.util.NoPosition.show
import ch.makery.address.util.DateUtil.DateFormatter
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.Alert.AlertType

@sfxml
class PersonOverviewController
(
  private val personTable: TableView[Person],
  private val firstNameCol: TableColumn[Person,String],
  private val lastNameCol: TableColumn[Person,String],
  private val firstNameLbl: Label,
  private val lastNameLbl: Label,
  private val streetLbl: Label,
  private val cityLbl: Label,
  private val postalCodeLbl: Label,
  private val birthdayLbl: Label
){
  personTable.items = MainApp.personData
  firstNameCol.cellValueFactory = {_.value.firstName}
  lastNameCol.cellValueFactory = {_.value.lastName}

  showPersonDetails(None)

  personTable.selectionModel().selectedItem.onChange(
    (_,_,newValue) => {showPersonDetails(Some(newValue))}
  )

  def showPersonDetails(person: Option[Person]): Unit={
    person match {
      case Some(person) =>
        //fill the labels with info from the person objective
        firstNameLbl.text <== person.firstName
        lastNameLbl.text <== person.lastName
        streetLbl.text <== person.street
        cityLbl.text <= person.city
        postalCodeLbl.text = person.postalCode.value.toString
        //birthday label required a special handling -> implicit class converter
        birthdayLbl.text = person.date.value.asString

      case None =>
        //Person in null, remove all the text
        firstNameLbl.text = ""
        lastNameLbl.text = ""
        streetLbl.text = ""
        postalCodeLbl.text = ""
        cityLbl.text = ""

    }
  }

  def handleDeletePerson(action:ActionEvent): Unit={
    val selectedIndex = personTable.selectionModel().getSelectedIndex
    if(selectedIndex >=0){
      personTable.items().remove(selectedIndex)
    }else{
      val alert = new Alert(AlertType.Warning){
        initOwner(MainApp.stage)
        title = "No Selection"
        headerText = "No Person Selected"
        contentText = "Please select a person in the table"
      }.showAndWait()
    }
  }
}