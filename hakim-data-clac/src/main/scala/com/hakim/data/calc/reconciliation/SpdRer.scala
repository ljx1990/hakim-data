package com.hakim.data.calc.reconciliation

import java.sql.Date

import akka.actor._
import com.hakim.data.calc.persistence.SpdBackRes
import slick.lifted.TableQuery
import slick.driver.MySQLDriver.api._

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}



//回款流水对账行为
case class BackSectionFlowReconciliation(date: String)
case class BackSectionFlowReconciliationResponse(date: String,isSuccess:Boolean)
//提现流水对账行为
case class WithdrawFlowReconciliation(date: String)
case class WithdrawFlowReconciliationResponse(date: String,isSuccess:Boolean)

case object EmptyRequest

/**
  * 浦发账的对账员
  * @author
  */
class SpdRer extends Actor with ActorLogging {
  val system = context.system

  case class SampleAck(remaining: Int)

  def receive = {
    case BackSectionFlowReconciliation(date) =>
      if (date.trim.nonEmpty) {
        val db = Database.forConfig("hakim_data")
        val spd = TableQuery[SpdBackRes]
        val queryFuture :Future[Seq[(Date, Int)]]= db.run(spd.result)
        val res:Seq[(Date, Int)] = Await.result(queryFuture, 5.seconds)
        print(res)
        sender() ! BackSectionFlowReconciliationResponse(date,true);
      }else{
        sender() ! BackSectionFlowReconciliationResponse(date,false);
      }

    case WithdrawFlowReconciliation(date) =>
      if (date.trim.nonEmpty) {
        //
        sender() ! WithdrawFlowReconciliationResponse(date,true);
      }else{
        sender() ! WithdrawFlowReconciliationResponse(date,false);
      }
    case _ =>
      //DO NOTHING
  }

}
