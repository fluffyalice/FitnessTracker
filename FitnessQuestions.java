package assignment2020.handoutquestions;

import java.util.*;

import assignment2020.codeprovided.fitnesstracker.Participant;
import assignment2020.codeprovided.fitnesstracker.Tracker;
import assignment2020.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2020.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2020.codeprovided.handoutquestions.AbstractFitnessQuestions;


public class FitnessQuestions extends AbstractFitnessQuestions {
	private Collection<Participant> participants;
    private int totalParticipants;
    private int participantsNumberWithHRM;
    private int participantsNumberWithStepsM;
    private int participantsNumberWithDistanceM;
    private int participantsNumberWithEEM;
	private int totalCountOfHeartRate;
	private int totalCountOfSteps;
	private int totalCountOfDistance;
	private int totalCountOfEE;
	private List<Integer> totalCountOfHeartRateFT;
	private int totalCountOfEEFT1;
	private List<Integer> totalCountOfStepsFT234;
	private int totalCountOfDisFT5;
	private Set<String> participantHighestSteps = new HashSet<String>();
	String[] pMaxStepsArray = new String[10];
	private Set<String> participantLowestSteps = new HashSet<String>();
	String[] pLowestStepsArray = new String[10];
	private Set<String> participantLowestDis = new HashSet<String>();
	String[] pMaxDisArray = new String[10];
	private Set<String> participantHighestDis = new HashSet<String>();
	String[] pLowestDisArray = new String[10];
	private double globalAvgHR;
	private List<String> avgHRAboveGlobalParticipants;
	String[] avgAboveArray = new String[10];
	private List<String> avgHRBelowGlobalParticipants;
	String[] avgBelowArray = new String[10];

	public FitnessQuestions(Collection<Participant> participants) {
		super(participants);
		this.participants = participants;
		totalParticipants = getTotalParticipants();
		participantsNumberWithHRM = getParticipantsNumberWithHRM();
		participantsNumberWithStepsM = getParticipantsNumberWithStepsM();
		participantsNumberWithDistanceM = getParticipantsNumberWithDistanceM();
		participantsNumberWithEEM = getParticipantsNumberWithEEM();
		totalCountOfHeartRate = getTotalHRMCount();
		totalCountOfSteps = getTotalStepsCount();
		totalCountOfDistance = getTotalDistanceCount();
		totalCountOfEE = getTotalEECount();
		totalCountOfHeartRateFT = getHRMCountPerFT();
		totalCountOfEEFT1 = getEEMCountForFT1();
		totalCountOfStepsFT234 = getStepsMCountForFT234();
		totalCountOfDisFT5 = getDistanceMCountForFT5();
		participantHighestSteps = getHighestNumberOfStepsParticipants();

		int index = 0;
		Iterator<String> it1 = participantHighestSteps.iterator();
		while (it1.hasNext()){
			String tempStr = it1.next();
			pMaxStepsArray[index] = tempStr;
			index++;
		}

		int index1 = 0;
		participantLowestSteps = getLowestNumberOfStepsParticipants();
		Iterator<String> it2 = participantLowestSteps.iterator();
		while (it2.hasNext()){
			String tempStr = it2.next();
			pLowestStepsArray[index1] = tempStr;
			index1++;
		}

		int index2 = 0;
		participantHighestDis = getHighestWalkedDistanceParticipants();
		Iterator<String> it3 = participantHighestDis.iterator();
		while (it3.hasNext()){
			String tempStr = it3.next();
			pMaxDisArray[index2] = tempStr;
			index2++;
		}

		int index3 = 0;
		participantLowestDis = getLowestWalkedDistanceParticipants();
		Iterator<String> it4 = participantLowestDis.iterator();
		while (it4.hasNext()){
			String tempStr = it4.next();
			pLowestDisArray[index3] = tempStr;
			index3++;
		}

		globalAvgHR = getGlobalAverageHR();

		int index4 = 0;
		avgHRAboveGlobalParticipants = getAvgHRAboveGlobalParticipants();
		Iterator<String> it5 = avgHRAboveGlobalParticipants.iterator();
		while (it5.hasNext()){
			String tempStr = it5.next();
			avgAboveArray[index4] = tempStr;
			index4++;
		}

		int index5 = 0;
		avgHRBelowGlobalParticipants = getAvgHRBelowGlobalParticipants();
		Iterator<String> it6 = avgHRBelowGlobalParticipants.iterator();
		while (it6.hasNext()){
			String tempStr = it6.next();
			avgBelowArray[index5] = tempStr;
			index5++;
		}

	}
	
	@Override
	public String toString() {
		// TODO Implement
		//return "TODO: FitnessQuestions' toString() method should return the answers to the questions as specified in the handout.\n";
		return "\nQ1. Total number of participants: "+totalParticipants+"\n"+
				"\nQ2. Number of participants with heart rate measurements: "+participantsNumberWithHRM+"\n"+
				"\nQ3. Number of participants with steps measurements: "+participantsNumberWithStepsM+"\n"+
				"\nQ4. Number of participants with distance measurements: "+participantsNumberWithDistanceM+"\n"+
				"\nQ5. Number of participants with distance measurements: "+participantsNumberWithEEM+"\n"+
				"\nQ6. Total count of heart rate measurements: "+totalCountOfHeartRate+"\n"+
				"\nQ7. Total count of steps measurements: "+totalCountOfSteps+"\n"+
				"\nQ8. Total count of distance measurements: "+totalCountOfDistance+"\n"+
				"\nQ9. Total count of energy expenditure measurements: "+totalCountOfEE+"\n"+
				"\nQ10. Total count of heart rate measurement for FT1: "+totalCountOfHeartRateFT.get(0)+
				"\n     Total count of heart rate measurement for FT2: "+totalCountOfHeartRateFT.get(1)+
				"\n     Total count of heart rate measurement for FT3: "+totalCountOfHeartRateFT.get(2)+
				"\n     Total count of heart rate measurement for FT4: "+totalCountOfHeartRateFT.get(3)+
				"\n     Total count of heart rate measurement for FT5: "+totalCountOfHeartRateFT.get(4)+"\n"+
				"\nQ11. Total count of energy expenditure measurement for FT1: "+totalCountOfEEFT1+"\n"+
				"\nQ12. Total count of energy expenditure measurement for FT2: "+totalCountOfStepsFT234.get(0)+
				"\n     Total count of energy expenditure measurement for FT3: "+totalCountOfStepsFT234.get(1)+
				"\n     Total count of energy expenditure measurement for FT4: "+totalCountOfStepsFT234.get(2)+"\n"+
				"\nQ13. Total count of energy distance for FT5: "+totalCountOfDisFT5+"\n"+
				"\nQ14. 10 participant/s with the highest number of steps:"+
				"\n     " + pMaxStepsArray[0]+
				"\n     " + pMaxStepsArray[1]+
				"\n     " + pMaxStepsArray[2]+
				"\n     " + pMaxStepsArray[3]+
				"\n     " + pMaxStepsArray[4]+
				"\n     " + pMaxStepsArray[5]+
				"\n     " + pMaxStepsArray[6]+
				"\n     " + pMaxStepsArray[7]+
				"\n     " + pMaxStepsArray[8]+
				"\n     " + pMaxStepsArray[9]+"\n"+
				"\nQ15. 10 participant/s with the lowest number of steps:"+
				"\n     " + pLowestStepsArray[0]+
				"\n     " + pLowestStepsArray[1]+
				"\n     " + pLowestStepsArray[2]+
				"\n     " + pLowestStepsArray[3]+
				"\n     " + pLowestStepsArray[4]+
				"\n     " + pLowestStepsArray[5]+
				"\n     " + pLowestStepsArray[6]+
				"\n     " + pLowestStepsArray[7]+
				"\n     " + pLowestStepsArray[8]+
				"\n     " + pLowestStepsArray[9]+"\n"+
				"\nQ16. 10 participant/s with the highest number of walked distance:"+
				"\n     " + pMaxDisArray[0]+
				"\n     " + pMaxDisArray[1]+
				"\n     " + pMaxDisArray[2]+
				"\n     " + pMaxDisArray[3]+
				"\n     " + pMaxDisArray[4]+
				"\n     " + pMaxDisArray[5]+
				"\n     " + pMaxDisArray[6]+
				"\n     " + pMaxDisArray[7]+
				"\n     " + pMaxDisArray[8]+
				"\n     " + pMaxDisArray[9]+"\n"+
				"\nQ17. 10 participant/s with the lowest number of walked distance:"+
				"\n     " + pLowestDisArray[0]+
				"\n     " + pLowestDisArray[1]+
				"\n     " + pLowestDisArray[2]+
				"\n     " + pLowestDisArray[3]+
				"\n     " + pLowestDisArray[4]+
				"\n     " + pLowestDisArray[5]+
				"\n     " + pLowestDisArray[6]+
				"\n     " + pLowestDisArray[7]+
				"\n     " + pLowestDisArray[8]+
				"\n     " + pLowestDisArray[9]+"\n"+
				"\nQ18. Global average heart rate for the whole dataset:"+globalAvgHR+"\n"+
				"\nQ19. 1 participant/s with heart raye above global average heart rate (120.3450276243094):"+
				"\n     " + avgAboveArray[0]+"\n"+
				"\nQ20. 9 participant/s with heart raye below global average heart rate (120.3450276243094):"+
				"\n     " + avgBelowArray[1]+
				"\n     " + avgBelowArray[2]+
				"\n     " + avgBelowArray[3]+
				"\n     " + avgBelowArray[4]+
				"\n     " + avgBelowArray[5]+
				"\n     " + avgBelowArray[6]+
				"\n     " + avgBelowArray[7]+
				"\n     " + avgBelowArray[8]+
				"\n     " + avgBelowArray[9]
				;
	}

	@Override
	public int getTotalParticipants() {
		// TODO Auto-generated method stub
		int numberOfParticipants = participants.size();
		return numberOfParticipants;
	}

	@Override
	public int getParticipantsNumberWithHRM() {
		// TODO Auto-generated method stub
		int counter = 0;
		boolean ifContainHeartRate;
		for(Participant p : participants){
			ifContainHeartRate = p.getTrackersMap().get("GS").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifContainHeartRate){
				counter++;
			}

		}

		return counter;
	}

	@Override
	public int getParticipantsNumberWithStepsM() {
		// TODO Auto-generated method stub
		int counter = 0;
		boolean ifContainSteps;
		for(Participant p : participants){
			ifContainSteps = p.getTrackersMap().get("GS").getMeasurementsMap().containsKey(MeasurementType.STEPS);
			if(ifContainSteps){
				counter++;
			}

		}

		return counter;
	}

	@Override
	public int getParticipantsNumberWithDistanceM() {
		// TODO Auto-generated method stub
		int counter = 0;
		boolean ifContainDistance;
		for(Participant p : participants){
			ifContainDistance = p.getTrackersMap().get("GS").getMeasurementsMap().containsKey(MeasurementType.DISTANCE);
			if(ifContainDistance){
				counter++;
			}

		}

		return counter;
	}

	@Override
	public int getParticipantsNumberWithEEM() {
		// TODO Auto-generated method stub
		int counter = 0;
		boolean ifContainEnergyExpenditure;
		for(Participant p : participants){
			ifContainEnergyExpenditure = p.getTrackersMap().get("GS").getMeasurementsMap().containsKey(MeasurementType.DISTANCE);
			if(ifContainEnergyExpenditure){
				counter++;
			}

		}

		return counter;
	}

	@Override
	public int getTotalHRMCount() {
		// TODO Auto-generated method stub
		int counter = 0;
		boolean ifContainHeartRate;
		for(Participant p : participants){
			int hrCount = 0;
			ifContainHeartRate = p.getTrackersMap().get("GS").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifContainHeartRate){
				hrCount = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				counter = counter + hrCount;
			}
		}

		return counter;
	}

	@Override
	public int getTotalStepsCount() {
		// TODO Auto-generated method stub
		int counter = 0;
		boolean ifContainHeartRate;
		for(Participant p : participants){
			int hrCount = 0;
			ifContainHeartRate = p.getTrackersMap().get("GS").getMeasurementsMap().containsKey(MeasurementType.STEPS);
			if(ifContainHeartRate){
				hrCount = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.STEPS).size();
				counter = counter + hrCount;
			}
		}

		return counter;
	}

	@Override
	public int getTotalDistanceCount() {
		// TODO Auto-generated method stub
		int counter = 0;
		boolean ifContainHeartRate;
		for(Participant p : participants){
			int hrCount = 0;
			ifContainHeartRate = p.getTrackersMap().get("GS").getMeasurementsMap().containsKey(MeasurementType.DISTANCE);
			if(ifContainHeartRate){
				hrCount = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.DISTANCE).size();
				counter = counter + hrCount;
			}
		}

		return counter;
	}

	@Override
	public int getTotalEECount() {
		// TODO Auto-generated method stub
		int counter = 0;
		boolean ifContainHeartRate;
		for(Participant p : participants){
			int hrCount = 0;
			ifContainHeartRate = p.getTrackersMap().get("GS").getMeasurementsMap().containsKey(MeasurementType.ENERGY_EXPENDITURE);
			if(ifContainHeartRate){
				hrCount = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.ENERGY_EXPENDITURE).size();
				counter = counter + hrCount;
			}
		}

		return counter;
	}

	@Override
	public List<Integer> getHRMCountPerFT() {
		// TODO Auto-generated method stub
		List<Integer> counterList = new ArrayList<Integer>();
		int FT1Counter = 0;
		int FT2Counter = 0;
		int FT3Counter = 0;
		int FT4Counter = 0;
		int FT5Counter = 0;

		boolean ifFT1ContainHeartRate,ifFT2ContainHeartRate,ifFT3ContainHeartRate,ifFT4ContainHeartRate,ifFT5ContainHeartRate;
		for(Participant p : participants){
			int FT1Count = 0;
			int FT2Count = 0;
			int FT3Count = 0;
			int FT4Count = 0;
			int FT5Count = 0;

			ifFT1ContainHeartRate = p.getTrackersMap().get("FT1").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifFT1ContainHeartRate){
				FT1Count = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				FT1Counter = FT1Counter + FT1Count;
			}

			ifFT2ContainHeartRate = p.getTrackersMap().get("FT2").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifFT2ContainHeartRate){
				FT2Count = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				FT2Counter = FT2Counter + FT2Count;
			}

			ifFT3ContainHeartRate = p.getTrackersMap().get("FT3").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifFT3ContainHeartRate){
				FT3Count = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				FT3Counter = FT3Counter + FT3Count;
			}

			ifFT4ContainHeartRate = p.getTrackersMap().get("FT4").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifFT4ContainHeartRate){
				FT4Count = p.getTrackersMap().get("FT4").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				FT4Counter = FT4Counter + FT4Count;
			}

			ifFT5ContainHeartRate = p.getTrackersMap().get("FT5").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifFT5ContainHeartRate){
				FT5Count = p.getTrackersMap().get("FT5").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				FT5Counter = FT5Counter + FT5Count;
			}

		}

		counterList.add(FT1Counter);
		counterList.add(FT2Counter);
		counterList.add(FT3Counter);
		counterList.add(FT4Counter);
		counterList.add(FT5Counter);
		return counterList;
	}

	@Override
	public int getEEMCountForFT1() {
		// TODO Auto-generated method stub
		int FT1Counter = 0;
		boolean ifFT1ContainEE;
		for(Participant p : participants){
			int FT1Count = 0;

			ifFT1ContainEE = p.getTrackersMap().get("FT1").getMeasurementsMap().containsKey(MeasurementType.ENERGY_EXPENDITURE);
			if(ifFT1ContainEE){
				FT1Count = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.ENERGY_EXPENDITURE).size();
				FT1Counter = FT1Counter + FT1Count;
			}
		}

		return FT1Counter;
	}

	@Override
	public List<Integer> getStepsMCountForFT234() {
		// TODO Auto-generated method stub
		List<Integer> counterList = new ArrayList<Integer>();
		int FT2Counter = 0;
		int FT3Counter = 0;
		int FT4Counter = 0;

		boolean ifFT2ContainSteps,ifFT3ContainSteps,ifFT4ContainSteps;
		for(Participant p : participants){
			int FT2Count = 0;
			int FT3Count = 0;
			int FT4Count = 0;

			ifFT2ContainSteps = p.getTrackersMap().get("FT2").getMeasurementsMap().containsKey(MeasurementType.STEPS);
			if(ifFT2ContainSteps){
				FT2Count = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.STEPS).size();
				FT2Counter = FT2Counter + FT2Count;
			}

			ifFT3ContainSteps = p.getTrackersMap().get("FT3").getMeasurementsMap().containsKey(MeasurementType.STEPS);
			if(ifFT3ContainSteps){
				FT3Count = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.STEPS).size();
				FT3Counter = FT3Counter + FT3Count;
			}

			ifFT4ContainSteps = p.getTrackersMap().get("FT4").getMeasurementsMap().containsKey(MeasurementType.STEPS);
			if(ifFT4ContainSteps){
				FT4Count = p.getTrackersMap().get("FT4").getMeasurementsMap().get(MeasurementType.STEPS).size();
				FT4Counter = FT4Counter + FT4Count;
			}

		}

		counterList.add(FT2Counter);
		counterList.add(FT3Counter);
		counterList.add(FT4Counter);
		return counterList;
	}

	@Override
	public int getDistanceMCountForFT5() {
		// TODO Auto-generated method stub
		int FT5Counter = 0;
		boolean ifFT5ContainDis;
		for(Participant p : participants){
			int FT5Count = 0;

			ifFT5ContainDis = p.getTrackersMap().get("FT5").getMeasurementsMap().containsKey(MeasurementType.ENERGY_EXPENDITURE);
			if(ifFT5ContainDis){
				FT5Count = p.getTrackersMap().get("FT5").getMeasurementsMap().get(MeasurementType.ENERGY_EXPENDITURE).size();
				FT5Counter = FT5Counter + FT5Count;
			}
		}

		return FT5Counter;
	}

	@Override
	public Set<String> getHighestNumberOfStepsParticipants() {
		// TODO Auto-generated method stub
        Set<String> pMaxStepsSet = new HashSet<>();
        String tempPMaxSteps;

		for(Participant p: participants){
			int tempMaxSteps = 0;
			int counter = 1;
			int tempSteps = 0;

			for(Tracker t: p.getAllTrackers()){
				List<Measurement> stepsList = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.STEPS);
				List<Measurement> stepsList1 = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.STEPS);
				List<Measurement> stepsList2 = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.STEPS);
				List<Measurement> stepsList3 = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.STEPS);
				List<Measurement> stepsList4 = p.getTrackersMap().get("FT4").getMeasurementsMap().get(MeasurementType.STEPS);
				List<Measurement> stepsList5 = p.getTrackersMap().get("FT5").getMeasurementsMap().get(MeasurementType.STEPS);
				stepsList.addAll(stepsList1);
				stepsList.addAll(stepsList2);
				stepsList.addAll(stepsList3);
				stepsList.addAll(stepsList4);
				stepsList.addAll(stepsList5);

				for(int i = 0; i<stepsList.size(); i++){
					tempSteps = (int) stepsList.get(i).getValue();

					if(counter == 1){
						tempMaxSteps = tempSteps;
				    }
					if(tempSteps > tempMaxSteps){
						tempMaxSteps = tempSteps;
					}
					counter = counter + 1;
				}

			}

			tempPMaxSteps = "*  Participant ID: " + p.getName() + " with number of steps: " + String.valueOf(tempMaxSteps);
			pMaxStepsSet.add(tempPMaxSteps);

		}
		System.out.println("\n");

		return pMaxStepsSet;
	}

	@Override
	public Set<String> getLowestNumberOfStepsParticipants() {
		// TODO Auto-generated method stub
		Set<String> pLowestStepsSet = new HashSet<>();
		String tempPLowstSteps;

		for(Participant p: participants){
			int tempLowestSteps = 0;
			int counter = 1;
			int tempSteps = 0;
			for(Tracker t: p.getAllTrackers()){
				List<Measurement> stepsList = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.STEPS);
				List<Measurement> stepsList1 = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.STEPS);
				List<Measurement> stepsList2 = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.STEPS);
				List<Measurement> stepsList3 = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.STEPS);
				List<Measurement> stepsList4 = p.getTrackersMap().get("FT4").getMeasurementsMap().get(MeasurementType.STEPS);
				List<Measurement> stepsList5 = p.getTrackersMap().get("FT5").getMeasurementsMap().get(MeasurementType.STEPS);
				stepsList.addAll(stepsList1);
				stepsList.addAll(stepsList2);
				stepsList.addAll(stepsList3);
				stepsList.addAll(stepsList4);
				stepsList.addAll(stepsList5);

				for(int i = 0; i<stepsList.size(); i++){
					tempSteps = (int) stepsList.get(i).getValue();

					if(counter == 1){
						tempLowestSteps = tempSteps;
					}
					if(tempSteps < tempLowestSteps){
						tempLowestSteps = tempSteps;
					}
					counter = counter + 1;
				}

			}

			tempPLowstSteps = "*  Participant ID: " + p.getName() + " with number of steps: " + String.valueOf(tempLowestSteps);
			pLowestStepsSet.add(tempPLowstSteps);

		}

		System.out.println("\n");
		return pLowestStepsSet;
	}

	@Override
	public Set<String> getHighestWalkedDistanceParticipants() {
		// TODO Auto-generated method stub
		Set<String> pMaxDisSet = new HashSet<>();
		String tempPMaxDis;

		for(Participant p: participants){
			double tempMaxDis = 0;
			int counter = 1;
			double tempDis = 0;
			for(Tracker t: p.getAllTrackers()){
				List<Measurement> disList = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.DISTANCE);
				List<Measurement> disList1 = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.DISTANCE);
				List<Measurement> disList2 = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.DISTANCE);
				List<Measurement> disList3 = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.DISTANCE);
				List<Measurement> disList4 = p.getTrackersMap().get("FT4").getMeasurementsMap().get(MeasurementType.DISTANCE);
				List<Measurement> disList5 = p.getTrackersMap().get("FT5").getMeasurementsMap().get(MeasurementType.DISTANCE);
				disList.addAll(disList1);
				disList.addAll(disList2);
				disList.addAll(disList3);
				disList.addAll(disList4);
				disList.addAll(disList5);

				for(int i = 0; i<disList.size(); i++){
					tempDis = (double) disList.get(i).getValue();

					if(counter == 1){
						tempMaxDis = tempDis;
					}
					if(tempDis > tempMaxDis){
						tempMaxDis = tempDis;
					}
					counter = counter + 1;
				}

			}

			tempPMaxDis = "*  Participant ID: " + p.getName() + " with number of walked distance: " + String.valueOf(tempMaxDis);
			pMaxDisSet.add(tempPMaxDis);

		}

		System.out.println("\n");
		return pMaxDisSet;
	}

	@Override
	public Set<String> getLowestWalkedDistanceParticipants() {
		// TODO Auto-generated method stub
		Set<String> pLowestDisSet = new HashSet<>();
		String tempPLowestDis;

		for(Participant p: participants){
			double tempLowestDis = 0;
			int counter = 1;
			double tempDis = 0;
			for(Tracker t: p.getAllTrackers()){
				List<Measurement> disList = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.DISTANCE);
				List<Measurement> disList1 = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.DISTANCE);
				List<Measurement> disList2 = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.DISTANCE);
				List<Measurement> disList3 = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.DISTANCE);
				List<Measurement> disList4 = p.getTrackersMap().get("FT4").getMeasurementsMap().get(MeasurementType.DISTANCE);
				List<Measurement> disList5 = p.getTrackersMap().get("FT5").getMeasurementsMap().get(MeasurementType.DISTANCE);
				disList.addAll(disList1);
				disList.addAll(disList2);
				disList.addAll(disList3);
				disList.addAll(disList4);
				disList.addAll(disList5);

				for(int i = 0; i<disList.size(); i++){
					tempDis = (double) disList.get(i).getValue();

					if(counter == 1){
						tempLowestDis = tempDis;
					}
					if(tempDis < tempLowestDis){
						tempLowestDis = tempDis;
					}
					counter = counter + 1;
				}

			}

			tempPLowestDis = "*  Participant ID: " + p.getName() + " with number of walked distance: " + String.valueOf(tempLowestDis);
			pLowestDisSet.add(tempPLowestDis);

		}

		System.out.println("\n");

		return pLowestDisSet;
	}

	@Override
	public double getGlobalAverageHR() {
		double totalCount = 0;
		double globalAvgHR = 0;

		int FT1Counter = 0;
		int FT2Counter = 0;
		int FT3Counter = 0;
		int FT4Counter = 0;
		int FT5Counter = 0;
		int GSCounter = 0;
		double value = 0;

		boolean ifFT1ContainHeartRate,ifFT2ContainHeartRate,ifFT3ContainHeartRate,ifFT4ContainHeartRate,ifFT5ContainHeartRate,ifGSContainHeartRate;
		
		for(Participant p: participants){
			int FT1Count = 0;
			int FT2Count = 0;
			int FT3Count = 0;
			int FT4Count = 0;
			int FT5Count = 0;
			int GSCount = 0;

			ifFT1ContainHeartRate = p.getTrackersMap().get("FT1").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifFT1ContainHeartRate){
				FT1Count = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				FT1Counter = FT1Counter + FT1Count;
			}

			ifFT2ContainHeartRate = p.getTrackersMap().get("FT2").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifFT2ContainHeartRate){
				FT2Count = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				FT2Counter = FT2Counter + FT2Count;
			}

			ifFT3ContainHeartRate = p.getTrackersMap().get("FT3").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifFT3ContainHeartRate){
				FT3Count = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				FT3Counter = FT3Counter + FT3Count;
			}

			ifFT4ContainHeartRate = p.getTrackersMap().get("FT4").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifFT4ContainHeartRate){
				FT4Count = p.getTrackersMap().get("FT4").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				FT4Counter = FT4Counter + FT4Count;
			}

			ifFT5ContainHeartRate = p.getTrackersMap().get("FT5").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifFT5ContainHeartRate){
				FT5Count = p.getTrackersMap().get("FT5").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				FT5Counter = FT5Counter + FT5Count;
			}

			ifGSContainHeartRate = p.getTrackersMap().get("GS").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifGSContainHeartRate){
				GSCount = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				GSCounter = GSCounter + GSCount;
			}

		}

		totalCount = FT1Counter+FT2Counter+FT3Counter+FT4Counter+FT5Counter+GSCounter;

		for(Participant p: participants){

			List<Measurement> List = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.HEART_RATE);
			List<Measurement> List1 = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.HEART_RATE);
			List<Measurement> List2 = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.HEART_RATE);
			List<Measurement> List3 = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.HEART_RATE);

			List.addAll(List1);
			List.addAll(List2);
			List.addAll(List3);

			for(int i = 0; i<List.size(); i++){
				value = (double) List.get(i).getValue()+value;
			}

		}

		globalAvgHR = value/totalCount;

		return globalAvgHR;
	}

	@Override
	public List<String> getAvgHRAboveGlobalParticipants() {
		List<String> avgHRAbovePList = new ArrayList<>();
		String tempAvgAbove;

		boolean ifAvgGSContainHeartRate,ifAvgFT1ContainHeartRate,ifAvgFT2ContainHeartRate,ifAvgFT3ContainHeartRate,ifAvgFT4ContainHeartRate;
		int avgGSCount = 0;
		int avgFT1Count = 0;
		int avgFT2Count = 0;
		int avgFT3Count = 0;

		int avgGSCounter = 0;
		int avgFT1Counter = 0;
		int avgFT2Counter = 0;
		int avgFT3Counter = 0;

		double avgGS = 0;
		double avgFT1 = 0;
		double avgFT2 = 0;
		double avgFT3 = 0;

		double avgGSValue = 0;
		double avgFT1Value = 0;
		double avgFT2Value = 0;
		double avgFT3Value = 0;

		double totalAvg = 0;
		double tempTotalAvg = 0;
		double numOfTrackers = 4;

		for(Participant p: participants){

			ifAvgGSContainHeartRate = p.getTrackersMap().get("GS").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifAvgGSContainHeartRate){
				avgGSCount = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				avgGSCounter = avgGSCounter + avgGSCount;
			}
			List<Measurement> GSList = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.HEART_RATE);
			for(int i = 0; i<GSList.size(); i++){
				avgGSValue = (double) GSList.get(i).getValue()+avgGSValue;
			}
			avgGS = avgGSValue/avgGSCounter;


			ifAvgFT1ContainHeartRate = p.getTrackersMap().get("FT1").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifAvgFT1ContainHeartRate){
				avgFT1Count = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				avgFT1Counter = avgFT1Counter + avgFT1Count;
			}
			List<Measurement> FT1List = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.HEART_RATE);
			for(int i = 0; i<FT1List.size(); i++){
				avgFT1Value = (double) FT1List.get(i).getValue()+avgFT1Value;
			}
			avgFT1 = avgFT1Value/avgFT1Counter;


			ifAvgFT2ContainHeartRate = p.getTrackersMap().get("FT2").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifAvgFT2ContainHeartRate){
				avgFT2Count = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				avgFT2Counter = avgFT2Counter + avgFT2Count;
			}
			List<Measurement> FT2List = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.HEART_RATE);
			for(int i = 0; i<FT2List.size(); i++){
				avgFT2Value = (double) FT2List.get(i).getValue()+avgFT2Value;
			}
			avgFT2 = avgFT2Value/avgFT2Counter;


			ifAvgFT3ContainHeartRate = p.getTrackersMap().get("FT3").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifAvgFT3ContainHeartRate){
				avgFT3Count = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				avgFT3Counter = avgFT3Counter + avgFT3Count;
			}
			List<Measurement> FT3List = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.HEART_RATE);
			for(int i = 0; i<FT3List.size(); i++){
				avgFT3Value = (double) FT3List.get(i).getValue()+avgFT3Value;
			}
			avgFT3 = avgFT3Value/avgFT3Counter;

			totalAvg = (avgFT1+avgFT2+avgFT3+avgGS)/numOfTrackers;

			if(totalAvg>globalAvgHR){
				tempTotalAvg = totalAvg;
			}

			tempAvgAbove = "*  Participant ID: " + p.getName() + " with individual average heart rate "+tempTotalAvg;
			avgHRAbovePList.add(tempAvgAbove);
		}

		return avgHRAbovePList;

	}

	@Override
	public List<String> getAvgHRBelowGlobalParticipants() {
		// TODO Auto-generated method stub
		List<String> avgHRBelowPList = new ArrayList<>();
		String tempAvgBelow;

		boolean ifAvgGSContainHeartRate,ifAvgFT1ContainHeartRate,ifAvgFT2ContainHeartRate,ifAvgFT3ContainHeartRate;
		int avgGSCount = 0;
		int avgFT1Count = 0;
		int avgFT2Count = 0;
		int avgFT3Count = 0;

		int avgGSCounter = 0;
		int avgFT1Counter = 0;
		int avgFT2Counter = 0;
		int avgFT3Counter = 0;

		double avgGS = 0;
		double avgFT1 = 0;
		double avgFT2 = 0;
		double avgFT3 = 0;

		double avgGSValue = 0;
		double avgFT1Value = 0;
		double avgFT2Value = 0;
		double avgFT3Value = 0;

		double totalAvg = 0;
		double tempTotalAvg = 0;

		double numOfTrackers = 4;

		for(Participant p: participants){

			ifAvgGSContainHeartRate = p.getTrackersMap().get("GS").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifAvgGSContainHeartRate){
				avgGSCount = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				avgGSCounter = avgGSCounter + avgGSCount;
			}
			List<Measurement> GSList = p.getTrackersMap().get("GS").getMeasurementsMap().get(MeasurementType.HEART_RATE);
			for(int i = 0; i<GSList.size(); i++){
				avgGSValue = (double) GSList.get(i).getValue()+avgGSValue;
			}
			avgGS = avgGSValue/avgGSCounter;


			ifAvgFT1ContainHeartRate = p.getTrackersMap().get("FT1").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifAvgFT1ContainHeartRate){
				avgFT1Count = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				avgFT1Counter = avgFT1Counter + avgFT1Count;
			}
			List<Measurement> FT1List = p.getTrackersMap().get("FT1").getMeasurementsMap().get(MeasurementType.HEART_RATE);
			for(int i = 0; i<FT1List.size(); i++){
				avgFT1Value = (double) FT1List.get(i).getValue()+avgFT1Value;
			}
			avgFT1 = avgFT1Value/avgFT1Counter;


			ifAvgFT2ContainHeartRate = p.getTrackersMap().get("FT2").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifAvgFT2ContainHeartRate){
				avgFT2Count = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				avgFT2Counter = avgFT2Counter + avgFT2Count;
			}
			List<Measurement> FT2List = p.getTrackersMap().get("FT2").getMeasurementsMap().get(MeasurementType.HEART_RATE);
			for(int i = 0; i<FT2List.size(); i++){
				avgFT2Value = (double) FT2List.get(i).getValue()+avgFT2Value;
			}
			avgFT2 = avgFT2Value/avgFT2Counter;


			ifAvgFT3ContainHeartRate = p.getTrackersMap().get("FT3").getMeasurementsMap().containsKey(MeasurementType.HEART_RATE);
			if(ifAvgFT3ContainHeartRate){
				avgFT3Count = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.HEART_RATE).size();
				avgFT3Counter = avgFT3Counter + avgFT3Count;
			}
			List<Measurement> FT3List = p.getTrackersMap().get("FT3").getMeasurementsMap().get(MeasurementType.HEART_RATE);
			for(int i = 0; i<FT3List.size(); i++){
				avgFT3Value = (double) FT3List.get(i).getValue()+avgFT3Value;
			}
			avgFT3 = avgFT3Value/avgFT3Counter;

			totalAvg = (avgFT1+avgFT2+avgFT3+avgGS)/numOfTrackers;

			if(totalAvg<globalAvgHR){
				tempTotalAvg = totalAvg;
			}

			tempAvgBelow = "*  Participant ID: " + p.getName() + " with individual average heart rate "+tempTotalAvg;
			avgHRBelowPList.add(tempAvgBelow);
		}

		return avgHRBelowPList;
	}
}
