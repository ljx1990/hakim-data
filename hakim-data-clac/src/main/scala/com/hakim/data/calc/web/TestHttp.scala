package com.hakim.data.calc.web

import akka.pattern._
import akka.util.Timeout
import com.hakim.data.calc.reconciliation.{BackSectionFlowReconciliation, BackSectionFlowReconciliationResponse, WithdrawFlowReconciliation, WithdrawFlowReconciliationResponse}
import org.squbs.unicomplex.RouteDefinition
import spray.http._
import spray.routing.Directives._
import spray.routing._

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Success

/**
  * Just a place to hold actor paths.
  */
object ActorPaths {
  // actor path = /user/ + cube-shortname + / + actor name
  val pingActorPath = "/user/calc/spdRer"
}

/**
  * The route definition.
  */
class SampleHttpSvc extends RouteDefinition {

  import context.dispatcher

  implicit val timeout: Timeout = 3 seconds

  import ActorPaths._

  override def route: Route =
    get {
      path("spdRer" / "back") {
        onComplete(context.actorSelection(pingActorPath) ? BackSectionFlowReconciliation("anonymous")) {
          case Success(BackSectionFlowReconciliationResponse(data,boolean)) => complete(data)
          case _ => complete(StatusCodes.BadRequest)

        }
      } ~
      path("spdRer" / "withdraw") {
        onComplete(context.actorSelection(pingActorPath) ? WithdrawFlowReconciliation("anonymous")) {
          case Success(WithdrawFlowReconciliationResponse(data,boolean)) => complete(data)
          case _ => complete(StatusCodes.BadRequest)

        }
      }
    }~
    complete("Hello!")
}
