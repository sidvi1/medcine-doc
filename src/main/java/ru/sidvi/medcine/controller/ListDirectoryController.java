package ru.sidvi.medcine.controller;

import ru.sidvi.medcine.ButtonsListener;
import ru.sidvi.medcine.model.ListModel;
import ru.sidvi.medcine.view.ListView;

import java.awt.event.ActionEvent;

// ListDirectoryController.java
// Handles user interaction with listeners.
// Calls View and Model as needed.
/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class ListDirectoryController implements ButtonsListener {

	private final ListModel dirModel;
	private final ListView dirView;

	public ListDirectoryController(ListModel model, ListView view) {
		this.dirModel = model;
		this.dirView = view;
		//... Add listeners to the view.
		view.addButtonsListener(this);
	}
	
	@Override
	public void addPerformed(ActionEvent e) {
		String text = dirView.getInputText();
		dirModel.addDirectory(text);
	}
	
	@Override
	public void deletePerformed(ActionEvent e) {
		dirModel.removeMultipleDirectory(dirView.getSelectedText());
		}
	}

