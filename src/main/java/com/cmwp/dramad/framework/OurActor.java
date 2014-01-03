package com.cmwp.dramad.framework;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import akka.actor.Props;
import akka.io.Tcp;


public abstract class OurActor extends UntypedActor {

	protected final ActorRef tcpManager = Tcp.get(getContext().system()).manager();
	protected ActorRef ourActorOf( Class actorClass ) {
		return getContext().actorOf(Props.create(actorClass));
	}

}
