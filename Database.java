//CSV�ǂݍ��ݗp�N���X
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.StringTokenizer;

//���X�g�p�N���X
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Database{
	//�t�B�[���h��`
	String transfer = "����"; //�抷�w

	public ArrayList<String> list_kotohira_stations = new ArrayList<>(); //�Օ����w�����X�g
	public ArrayList<String> list_shido_stations = new ArrayList<>(); //�u�x���w�����X�g
	public ArrayList<String> list_nagao_stations = new ArrayList<>(); //�������w�����X�g
	public ArrayList<ArrayList<String>> list_all_stations = new ArrayList<ArrayList<String>>(); //�S���w�����X�g
	public ArrayList<ArrayList<String>> list_fare = new ArrayList<ArrayList<String>>(); //�^�����X�g
	public ArrayList<ArrayList<String>> list_kotohira_timetable_up = new ArrayList<ArrayList<String>>(); //�Օ�����莞���\
	public ArrayList<ArrayList<String>> list_shido_timetable_up = new ArrayList<ArrayList<String>>(); //�u�x����莞���\
	public ArrayList<ArrayList<String>> list_nagao_timetable_up = new ArrayList<ArrayList<String>>(); //��������莞���\
	public ArrayList<ArrayList<String>> list_kotohira_timetable_down = new ArrayList<ArrayList<String>>(); //�Օ������莞���\
	public ArrayList<ArrayList<String>> list_shido_timetable_down = new ArrayList<ArrayList<String>>(); //�u�x�����莞���\
	public ArrayList<ArrayList<String>> list_nagao_timetable_down = new ArrayList<ArrayList<String>>(); //���������莞���\

	//�R���X�g���N�^(���X�g��CSV�t�@�C���f�[�^���i�[)
	public Database(){
		readCSV1(list_kotohira_stations, "�H���\_�Օ���.csv"); //�Օ����w�����X�g
		readCSV1(list_shido_stations, "�H���\_�u�x��.csv"); //�u�x���w�����X�g
		readCSV1(list_nagao_stations, "�H���\_������.csv"); //�������w�����X�g
		list_all_stations.add(list_kotohira_stations);
		list_all_stations.add(list_shido_stations);
		list_all_stations.add(list_nagao_stations);
		readCSV2(list_fare, "�^���\.csv"); //�^�����X�g
		readCSV2(list_kotohira_timetable_up, "�����\_�Օ������.csv"); //�Օ�����莞���\
		readCSV2(list_shido_timetable_up, "�����\_�u�x�����.csv"); //�u�x����莞���\
		readCSV2(list_nagao_timetable_up, "�����\_���������.csv"); //��������莞���\
		readCSV2(list_kotohira_timetable_down, "�����\_�Օ�������.csv"); //�Օ������莞���\
		readCSV2(list_shido_timetable_down, "�����\_�u�x������.csv"); //�u�x�����莞���\
		readCSV2(list_nagao_timetable_down, "�����\_����������.csv"); //���������莞���\
	}


	//���\�b�h��`
	//1�������X�g��CSV�t�@�C���f�[�^���i�[(��1����:�i�[���X�g, ��2����:CSV�t�@�C����)
	public void readCSV1(ArrayList<String> A1, String file){
		try {
			File csv = new File(file); // CSV�f�[�^�t�@�C��
			BufferedReader br = new BufferedReader(new FileReader(csv));
			String line = "";
			//�ǂݍ��ݏI���܂Ń��X�g�Ɋi�[
			while ((line = br.readLine()) != null) {
				String[] elems = line.split(","); //�J���}�ŕ����񕪊�
				for(String elem : elems){
					A1.add(elem); //1�������X�g�Ɋi�[
				}
			}
			br.close();
			//�G���[����
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//2�������X�g��CSV�t�@�C���f�[�^���i�[(��1����:�i�[���X�g, ��2����:CSV�t�@�C����)
	public void readCSV2(ArrayList<ArrayList<String>> A2, String file){
		try {
			File csv = new File(file); // CSV�f�[�^�t�@�C��
			BufferedReader br = new BufferedReader(new FileReader(csv));
			String line = "";
			//�ǂݍ��ݏI���܂Ń��X�g�Ɋi�[
			while ((line = br.readLine()) != null) {
				ArrayList<String> array = new ArrayList<>(); //�ꎟ�ۑ��p���X�g
				String[] elems = line.split(","); //�J���}�ŕ����񕪊�
				for(String elem : elems){ //�s�Ɋi�[
					array.add(elem);
				}
				A2.add(array); //�s��2�������X�g�Ɋi�[
			}
			br.close();
			//�G���[����
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//2�������X�g��indexOf()���\�b�h(�Ԃ�l�̓q�b�g�����s�ԍ�(�Ȃ��̏ꍇ-1))
	public int indexOf2(ArrayList<ArrayList<String>> A2, String str){
		int result = -1;
		for(int i=0; i<A2.size(); i++){
			if(A2.get(i).indexOf(str) != -1){
				result = i;
				break;
			}
		}
		return result;
	}

	//�o���w�Ɠ����w����͂���ƁA��菇��Ԃ�
	public String showRoot(String depart, String arrival){
		int a = indexOf2(list_all_stations, depart);
		int b = indexOf2(list_all_stations, arrival);
		if(a == b || depart.equals(transfer) || arrival.equals(transfer)){
			return (depart + "-" + arrival + "_" + Math.max(a, b));
		}else{
			return (depart + "-" + transfer + "_" + a + "," + transfer + "-" + arrival + "_" + b);
		}
	}

	//�w�莞���ɍł��߂��ւ̏o�������A���������A���v���Ԃ�\������
	//info:starion1-station2_number[,station2-station3_number]
	public void showHowlong(int hour, int minute, String info){
		String[] array = info.split(",");
		//��芷�����Ȃ��ꍇ
		if(array.length == 1){
		}
	}
}
