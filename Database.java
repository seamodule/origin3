//CSV読み込み用クラス
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.StringTokenizer;

//リスト用クラス
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Database{
	//フィールド定義
	String transfer = "瓦町"; //乗換駅

	public ArrayList<String> list_kotohira_stations = new ArrayList<>(); //琴平線駅順リスト
	public ArrayList<String> list_shido_stations = new ArrayList<>(); //志度線駅順リスト
	public ArrayList<String> list_nagao_stations = new ArrayList<>(); //長尾線駅順リスト
	public ArrayList<ArrayList<String>> list_all_stations = new ArrayList<ArrayList<String>>(); //全線駅順リスト
	public ArrayList<ArrayList<String>> list_fare = new ArrayList<ArrayList<String>>(); //運賃リスト
	public ArrayList<ArrayList<String>> list_kotohira_timetable_up = new ArrayList<ArrayList<String>>(); //琴平線上り時刻表
	public ArrayList<ArrayList<String>> list_shido_timetable_up = new ArrayList<ArrayList<String>>(); //志度線上り時刻表
	public ArrayList<ArrayList<String>> list_nagao_timetable_up = new ArrayList<ArrayList<String>>(); //長尾線上り時刻表
	public ArrayList<ArrayList<String>> list_kotohira_timetable_down = new ArrayList<ArrayList<String>>(); //琴平線下り時刻表
	public ArrayList<ArrayList<String>> list_shido_timetable_down = new ArrayList<ArrayList<String>>(); //志度線下り時刻表
	public ArrayList<ArrayList<String>> list_nagao_timetable_down = new ArrayList<ArrayList<String>>(); //長尾線下り時刻表

	//コンストラクタ(リストにCSVファイルデータを格納)
	public Database(){
		readCSV1(list_kotohira_stations, "路線表_琴平線.csv"); //琴平線駅順リスト
		readCSV1(list_shido_stations, "路線表_志度線.csv"); //志度線駅順リスト
		readCSV1(list_nagao_stations, "路線表_長尾線.csv"); //長尾線駅順リスト
		list_all_stations.add(list_kotohira_stations);
		list_all_stations.add(list_shido_stations);
		list_all_stations.add(list_nagao_stations);
		readCSV2(list_fare, "運賃表.csv"); //運賃リスト
		readCSV2(list_kotohira_timetable_up, "時刻表_琴平線上り.csv"); //琴平線上り時刻表
		readCSV2(list_shido_timetable_up, "時刻表_志度線上り.csv"); //志度線上り時刻表
		readCSV2(list_nagao_timetable_up, "時刻表_長尾線上り.csv"); //長尾線上り時刻表
		readCSV2(list_kotohira_timetable_down, "時刻表_琴平線下り.csv"); //琴平線下り時刻表
		readCSV2(list_shido_timetable_down, "時刻表_志度線下り.csv"); //志度線下り時刻表
		readCSV2(list_nagao_timetable_down, "時刻表_長尾線下り.csv"); //長尾線下り時刻表
	}


	//メソッド定義
	//1次元リストにCSVファイルデータを格納(第1引数:格納リスト, 第2引数:CSVファイル名)
	public void readCSV1(ArrayList<String> A1, String file){
		try {
			File csv = new File(file); // CSVデータファイル
			BufferedReader br = new BufferedReader(new FileReader(csv));
			String line = "";
			//読み込み終わるまでリストに格納
			while ((line = br.readLine()) != null) {
				String[] elems = line.split(","); //カンマで文字列分割
				for(String elem : elems){
					A1.add(elem); //1次元リストに格納
				}
			}
			br.close();
			//エラー処理
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//2次元リストにCSVファイルデータを格納(第1引数:格納リスト, 第2引数:CSVファイル名)
	public void readCSV2(ArrayList<ArrayList<String>> A2, String file){
		try {
			File csv = new File(file); // CSVデータファイル
			BufferedReader br = new BufferedReader(new FileReader(csv));
			String line = "";
			//読み込み終わるまでリストに格納
			while ((line = br.readLine()) != null) {
				ArrayList<String> array = new ArrayList<>(); //一次保存用リスト
				String[] elems = line.split(","); //カンマで文字列分割
				for(String elem : elems){ //行に格納
					array.add(elem);
				}
				A2.add(array); //行を2次元リストに格納
			}
			br.close();
			//エラー処理
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//2次元リスト版indexOf()メソッド(返り値はヒットした行番号(なしの場合-1))
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

	//出発駅と到着駅を入力すると、乗り順を返す
	public String showRoot(String depart, String arrival){
		int a = indexOf2(list_all_stations, depart);
		int b = indexOf2(list_all_stations, arrival);
		if(a == b || depart.equals(transfer) || arrival.equals(transfer)){
			return (depart + "-" + arrival + "_" + Math.max(a, b));
		}else{
			return (depart + "-" + transfer + "_" + a + "," + transfer + "-" + arrival + "_" + b);
		}
	}

	//指定時刻に最も近い便の出発時刻、到着時刻、所要時間を表示する
	//info:starion1-station2_number[,station2-station3_number]
	public void showHowlong(int hour, int minute, String info){
		String[] array = info.split(",");
		//乗り換えがない場合
		if(array.length == 1){
		}
	}
}
