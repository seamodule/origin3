class Menu{
	private java.util.Scanner scan = new java.util.Scanner(System.in);
	public int distance;

	public void displayMenu(){
		Database data = new Database();
		System.out.println("„„„‚æ‚¤‚±‚»ƒƒƒ");
		while(true){
			System.out.println("”­’…‰w‚ğ“ü—Í‚µ‚Ä‚­‚¾‚³‚¢II");
			System.out.print("o”­‰w>>");
			String depart = scan.nextLine();
			if(depart.equals("q")){
				System.out.println("‚Ü‚½‚Ì‚²—˜—p‚ğ‚¨‘Ò‚¿‚µ‚Ä‚¨‚è‚Ü‚·B");
				System.exit(-1);
			}
			System.out.print("“’…‰w>>");
			String arrival = scan.nextLine();
			if(arrival.equals("q")){
				System.out.println("‚Ü‚½‚Ì‚²—˜—p‚ğ‚¨‘Ò‚¿‚µ‚Ä‚¨‚è‚Ü‚·B");
				System.exit(-1);
			}

			//int ind_depart = data.list_shido_stations.indexOf(depart);
			//int ind_arrival = data.list_shido_stations.indexOf(arrival);

			int index = data.indexOf2(data.list_fare, depart + "-" + arrival);
			if(index != -1){
				System.out.println(depart + " - " + arrival + " ŠÔ‚Ì‰^’ÀŒvZ‚ğ‚µ‚Ü‚·I");
				System.out.println("‰^’À‚Í" + data.list_fare.get(index).get(1) + "‰~‚Å‚·I\n");
				System.out.println(data.showRoot(depart, arrival));
			}else{
				System.out.println("Ä“ü—Í‚ğ‚¨Šè‚¢‚µ‚Ü‚·B\n");
			}
		}
	}
}
