package com.hakim.data.calc.reconciliation

import akka.actor._


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
        //
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
