class Menu{
	private java.util.Scanner scan = new java.util.Scanner(System.in);
	public int distance;

	public void displayMenu(){
		Database data = new Database();
		System.out.println("＞＞＞ようこそ＜＜＜");
		while(true){
			System.out.println("発着駅を入力してください！！");
			System.out.print("出発駅>>");
			String depart = scan.nextLine();
			if(depart.equals("q")){
				System.out.println("またのご利用をお待ちしております。");
				System.exit(-1);
			}
			System.out.print("到着駅>>");
			String arrival = scan.nextLine();
			if(arrival.equals("q")){
				System.out.println("またのご利用をお待ちしております。");
				System.exit(-1);
			}

			//int ind_depart = data.list_shido_stations.indexOf(depart);
			//int ind_arrival = data.list_shido_stations.indexOf(arrival);

			int index = data.indexOf2(data.list_fare, depart + "-" + arrival);
			if(index != -1){
				System.out.println(depart + " - " + arrival + " 間の運賃計算をします！");
				System.out.println("運賃は" + data.list_fare.get(index).get(1) + "円です！\n");
				System.out.println(data.showRoot(depart, arrival));
			}else{
				System.out.println("再入力をお願いします。\n");
			}
		}
	}
}
