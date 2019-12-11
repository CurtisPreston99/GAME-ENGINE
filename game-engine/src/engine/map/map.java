package engine.map;

import java.util.Arrays;

import engine.window;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class map {
	int width, height;
	int[][] map;
	tile[] tiles;
	JSONObject mapfile;
	String[][][] mapdata;

	public map(String f, window w) {
		try {
			mapfile = w.Loader.loadJSON(f);
		} catch (Exception e) {
			System.err.println("file not found/ bad JSON formatting");
			e.printStackTrace();
		}
		// get width and height
		this.width = mapfile.getInt("width");
		this.height = mapfile.getInt("height");

		// init map array
		map = new int[this.width][this.height];

		// making map array
		JSONArray m = mapfile.getJSONArray("map");
		for (int i = 0; i < width; i++) {
			JSONArray mm = m.getJSONArray(i);
			map[i] = mm.getIntArray();

		}
		// data layers
		if (mapfile.getJSONArray("mapdata").size() > 0) {
			JSONArray JSONmapdata = mapfile.getJSONArray("mapdata");
			mapdata = new String[JSONmapdata.size()][width][height];
			for (int i = 0; i < JSONmapdata.size(); i++) {
				String[][] mapdatalayer = new String[width][height];
				JSONArray datalayer = JSONmapdata.getJSONArray(i);
				for (int e = 0; e < datalayer.size(); e++) {
					mapdatalayer[e] = datalayer.getJSONArray(e).getStringArray();
				}
				mapdata[i] = mapdatalayer;
			}
		}
		tiles = new tile[mapfile.getJSONArray("tiles").size()];
		for (int i = 0; i < mapfile.getJSONArray("tiles").size(); i++) {
			tiles[i] = new tile(mapfile.getJSONArray("tiles").getString(i), w);
		}
	}

	public String getDataCell(int layer, int x, int y) {
		if (layer > mapdata.length) {
			throw new IllegalArgumentException("layer out of range");
		}
		if (layer < 0) {
			throw new IllegalArgumentException("layer must be>=0");
		}
		if (x < 0 || x > width) {
			throw new IllegalArgumentException("x must be >0 and <width");
		}
		if (y < 0 || y > width) {
			throw new IllegalArgumentException("y must be >0 and <height");
		}
		return mapdata[layer][x][y];
	}

	@Override
	public String toString() {
		StringBuilder o = new StringBuilder();
		o.append("MAP\n");
		o.append("tiles\n");
		o.append(Arrays.toString(tiles));
		o.append("\n");
		o.append("level\n");
		for (int[] i : map) {
			o.append(Arrays.toString(i));
			o.append("\n");
		}
		o.append("----------\n");
		o.append("dataLayers\n");

		for (int i = 0; i < mapdata.length; i++) {
			String[][] layer = mapdata[i];
			o.append("data Layer " + String.valueOf(i) + "\n");
			for (String[] e : layer) {
				o.append(Arrays.toString(e));
				o.append("\n");
			}

		}

		return o.toString();

	}

	public map(int width, int hight, window w) {
		map = new int[width][hight];

	}

}
