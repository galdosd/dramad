package com.cmwp.dramad.framework;

import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import akka.actor.Props;
import akka.io.Tcp;


public abstract class OurActor extends UntypedActor {

	protected final ActorRef tcpManager = Tcp.get(getContext().system()).manager();
	protected ActorRef ourActorOf( Class actorClass ) {
		return getContext().actorOf(Props.create(actorClass));
	}

	public static ActorRef actorFrom( ActorRefFactory system ) {
		Props props = Props.create( getClassStatic() );
		return
			(null!=system) 
			? system.actorOf(props)
			: null
		;
	}

	public static Class getClassStatic() {
		Class currentClass = new Object() { }.getClass().getEnclosingClass();
		return currentClass;
	}

}
