package com.cmwp.dramad;

import com.cmwp.dramad.framework.OurActor;
import com.cmwp.dramad.dept.*;

import akka.actor.Props;
import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;



public class DaemonActor extends OurActor {
	private ActorRef connectionDept;
	//private ActorRef commandDept;
	//private ActorRef endpointDept;
	//private ActorRef interserverDept;

	public enum Msg {
	}

	/*
	 * set up client listener socket(s)
	 */
	@Override
	public void preStart() {
		System.out.println( " Startin listener sockkits !" );
		connectionDept = ourActorOf(ConnectionDept.class);
		connectionDept.tell("hi!", getSelf());
		//commandDept = ourActorOf(CommandDept.class);
		//endpointDept  = ourActorOf(EndpointDept.class);
		//interserverDept  = ourActorOf(InterserverDept.class);

	}

	public void onReceive( Object msg ) {
		System.out.println( " We got signal ! " );
	}

}

