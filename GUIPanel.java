package assignment2020.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import assignment2020.codeprovided.fitnesstracker.Participant;
import assignment2020.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2020.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2020.codeprovided.gui.AbstractGUIPanel;

public class GUIPanel extends AbstractGUIPanel {

	private static final long serialVersionUID = -1257936627636425453L;
	protected String selectedParticipantName;
	protected String selectedTrackerName;
	protected MeasurementType selectedMeasurementType;
	protected boolean ifGoldStandardSelected;
	protected int selectedParticipantAge;
	protected String selectedGender;
	protected int selectedNumOfParticipant;
	protected List<Measurement> selectedMeasurementsList;

	public GUIPanel(Collection<Participant> participants) {
		super(participants);
		// TODO Auto-generated constructor stub

		selectedParticipantName = getSelectedParticipantName();
		selectedTrackerName = getSelectedTrackersNames();
		selectedMeasurementType  = getSelectedMeasurementType();
		ifGoldStandardSelected = isGoldStandardSelected();
		selectedParticipantAge = getSelectedParticipantAge(selectedParticipantName);
		selectedGender = getSelectedParticipantGender(selectedParticipantName);
		selectedNumOfParticipant = getSelectedNumOfParticipant();
	}

	public List<Measurement> getSelectedMeasurementsList(String pName, String pTrackerName, MeasurementType mType){
		List<Measurement> measurementList = new ArrayList<>();

		for(Participant p: participants){
			if(p.getName().equals(pName)){
				if(mType.getMeasurementName() == "Distance"){
					measurementList = p.getTrackersMap().get(pTrackerName).getMeasurementsMap().get(MeasurementType.DISTANCE);
				}else if(mType.getMeasurementName() == "Heart Rate"){
					measurementList = p.getTrackersMap().get(pTrackerName).getMeasurementsMap().get(MeasurementType.HEART_RATE);
				}else if(mType.getMeasurementName() == "Energy expenditure"){
					measurementList = p.getTrackersMap().get(pTrackerName).getMeasurementsMap().get(MeasurementType.ENERGY_EXPENDITURE);
				}else if(mType.getMeasurementName() == "Steps"){
					measurementList = p.getTrackersMap().get(pTrackerName).getMeasurementsMap().get(MeasurementType.STEPS);
				}
				break;
			}

		}

		return measurementList;
	}

	public int getSelectedParticipantAge(String selectedPName){
		int selectedPAge = 0;
		for(Participant p: participants){
			if(p.getName().equals(selectedPName)){
				selectedPAge = p.getAge();
			}
		}

		return selectedPAge;
	}

	public String getSelectedParticipantGender(String selectedPName){
		String selectedPGender = "";
		for(Participant p: participants){
			if(p.getName().equals(selectedPName)){
				selectedPGender = p.getGender();
				if(selectedPGender.equals("f")){
					selectedPGender = "Female";
				}else if(selectedPGender.equals("m")){
					selectedPGender = "Male";
				}
			}

		}

		return selectedPGender;
	}

	public int getSelectedNumOfParticipant(){
		selectedNumOfParticipant = participants.size();
		return selectedNumOfParticipant;
	}

	public void displayVisualizedCurve(String selectedParticipantName, String selectedTrackerName, int selectedAge, String selectedGender ){
		visualisedDataDetailsTextArea.setText("Participant ID: "+selectedParticipantName+"\n"+"Tracker: "+selectedTrackerName+"\n"+"Age: "+selectedAge+"\n"+"Gender: "+selectedGender);

	}

	public void displayDatasetDiscription(MeasurementType selectedMeasurementType, boolean ifGoldStandardSelected, int selectedNumOfParticipant){
		datasetGeneralDetailsTextArea.setText("MeasurementType: "+selectedMeasurementType+"\n"+"If the gold standard is selected: "+ifGoldStandardSelected+"\n"+
		"Number of participant: "+selectedNumOfParticipant);
	}

	@Override
	public void addListeners() {
		// TODO add the event handlers here
		displayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedParticipantName = getSelectedParticipantName();
				selectedTrackerName = getSelectedTrackersNames();
				selectedMeasurementType  = getSelectedMeasurementType();
				ifGoldStandardSelected = isGoldStandardSelected();
				selectedParticipantAge = getSelectedParticipantAge(selectedParticipantName);
				selectedGender = getSelectedParticipantGender(selectedParticipantName);
				selectedNumOfParticipant = getSelectedNumOfParticipant();
				selectedMeasurementsList = getSelectedMeasurementsList(selectedParticipantName,selectedTrackerName,selectedMeasurementType);
				repaint();
				curvesPanel.plotTest(selectedTrackerName);
				curvesPanel.passPlotData(selectedMeasurementsList,selectedMeasurementType);
				displayDatasetDiscription(selectedMeasurementType, ifGoldStandardSelected, selectedNumOfParticipant);
				displayVisualizedCurve(selectedParticipantName, selectedTrackerName, selectedParticipantAge, selectedGender);
			}
		});
	}

	@Override
	public String getSelectedParticipantName() {
		// TODO Auto-generated method stub
		String selectedParticipant = (String)comboListParticipants.getSelectedItem();
		return selectedParticipant;
	}

	@Override
	public String getSelectedTrackersNames() {
		// TODO Auto-generated method stub
		String selectedTracker = (String)comboListTrackers.getSelectedItem();
		return selectedTracker;
	}

	@Override
	public MeasurementType getSelectedMeasurementType() {
		// TODO Auto-generated method stub
		String stringSelectedMeasurementType = (String)comboListMeasurementType.getSelectedItem();
		MeasurementType selectedType = MeasurementType.fromMeasurementName(stringSelectedMeasurementType);
		return selectedType;
	}

	@Override
	public boolean isGoldStandardSelected() {
		// TODO Auto-generated method stub
		boolean ifGoldStandardSelected = (boolean)checkboxGoldStandard.isSelected();
		return ifGoldStandardSelected;
	}

}
