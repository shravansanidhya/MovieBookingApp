package com.mindtree.uility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mindtree.model.Seat;
import com.mindtree.model.Show;

/**
 * @author M1050831
 * 
 *         provides list of seat objects
 */
public class SeatProvider {

	/** defines cappacity of the List */
	private static final int capacity = 50;

	/**
	 * generates list of seats
	 */
	public static List<Seat> generateSeatList(Show show) {

		List<Seat> seatList = new ArrayList<>();

		Map<String, Integer> generatorVariables = new HashMap<String, Integer>();

		generatorVariables.put("counter", 0);
		generatorVariables.put("row", 65);
		generatorVariables.put("count", 1);

		while (generatorVariables.get("counter") < capacity) {
			int limiter = generatorVariables.get("count") + 1;
			String seatNumber = "";
			for (; generatorVariables.get("count") < limiter; generatorVariables.put("count",
					generatorVariables.get("count") + 1)) {

				seatNumber = (char) Integer.parseInt(generatorVariables.get("row") + "") + ""
						+ generatorVariables.get("count");

				if (generatorVariables.get("count") == 10) {
					generatorVariables.put("count", 0);
					generatorVariables.put("row", generatorVariables.get("row") + 1);
					limiter = 0;
				}

//				System.out.print(seatNumber + " ");
			}
//			System.out.print(seatNumber + " ");
			generatorVariables.put("counter", generatorVariables.get("counter") + 1);

			Seat seat = new Seat();
			seat.setSeatNumber(seatNumber);
			seat.setSeatStatus("Available");
			seat.setSeatClass("Silver");

//			Show show1 = new Show();
//			show1.setShowId(show.getShowId());

			seat.setShow(show);

			seatList.add(seat);

		}
		return seatList;

	}
}
