package com.hakim.data.calc.persistence
import java.sql.Date

import slick.driver.MySQLDriver.api._

/**
  * Created by hd on 2017/2/5.
  */
// Definition of the SUPPLIERS table
class SpdBackRes(tag: Tag) extends Table[(Date, Int)](tag, "spd_back_res") {
  def rerDate = column[Date]("rer_date")
  def isSuccess = column[Int]("is_success")
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (rerDate,isSuccess)
}

