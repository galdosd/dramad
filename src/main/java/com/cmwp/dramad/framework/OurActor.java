package com.cmwp.dramad.framework;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import akka.actor.Props;


public abstract class OurActor extends UntypedActor {

	protected ActorRef ourActorOf( Class actorClass ) {
		return getContext().actorOf(Props.create(actorClass));
	}

}
