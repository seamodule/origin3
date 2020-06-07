//CSV読み込み用クラス
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//リスト用クラス
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Main{
	public static void main(String[] args){
		Database data = new Database();
		//System.out.println(data.list_kotohira_stations);
		//System.out.println(data.list_shido_stations);
		//System.out.println(data.list_nagao_stations);
		System.out.println(data.list_all_stations.get(0));
		System.out.println(data.list_all_stations.get(1));
		System.out.println(data.list_all_stations.get(2));
		System.out.println(data.list_all_stations);
		Menu menu = new Menu();
		menu.displayMenu();
	}
}
