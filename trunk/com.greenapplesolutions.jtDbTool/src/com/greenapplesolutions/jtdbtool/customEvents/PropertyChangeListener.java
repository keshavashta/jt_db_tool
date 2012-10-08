package com.greenapplesolutions.jtdbtool.customEvents;


import java.util.EventListener;

public interface PropertyChangeListener extends EventListener {
	public void propertyChanged(PropertyChangeEvent evt);
}
