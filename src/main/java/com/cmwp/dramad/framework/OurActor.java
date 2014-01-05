package com.cmwp.dramad.framework;

import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import akka.actor.Props;
import akka.io.Tcp;


public abstract class OurActor extends UntypedActor {

	protected final ActorRef tcpManager = Tcp.get(getContext().system()).manager();

	protected void reply( Object msg ) {
		getSender().tell( msg, getSelf() );
	}

	protected ActorRef ourActorOf( Class actorClass ) {
		return getContext().actorOf(Props.create(actorClass));
	}

	public void onReceive( Object msg ) {
		logMsg( msg );
		if( !dispatchByType(msg) ) unhandled(msg);
	}

	protected boolean dispatchByType( Object msg ) {
		return false;
	}

	protected void logMsg( Object msg ) {
		// debugging (XXX: replace with something akka builtin probably)
		// and/or log4j
		if( null != msg ) {
			System.out.println(msg.getClass().getName() + "("+ msg.toString() +") --> " + this.getClass().getName());
		}
		else {
			System.out.println("null message -->" + this.getClass().getName());
		}
	}
}
