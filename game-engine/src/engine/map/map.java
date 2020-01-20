package engine.map;

import java.util.Arrays;

import engine.window;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class map {
	public int width, height;
	public int tileWidth, tileHeight;
	int[][] map;
	public tile[] tiles;
	JSONObject mapfile;
	String[][][] mapdata;
	window w;

	public map(int width, int hight, int tileWidth, int tileHeight, window w) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.map = new int[width][hight];
		this.mapdata = new String[1][width][height];
		for (int i = 0; i < map.length; i++) {
			for (int e = 0; e < map[i].length; e++) {
				this.map[i][e] = -1;
				this.mapdata[0][i][e] = "";
			}
		}
		this.tiles = new tile[0];
		this.w = w;

	}

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
		this.tileWidth = mapfile.getInt("tileWidth");
		this.tileHeight = mapfile.getInt("tileHeight");

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

	public void resize(int w, int h) {
		int[][] newmap = new int[w][h];
		String[][][] newmapdata = new String[this.mapdata.length][w][h];
		//init data layers, and seting all data ids
			for(int i=0;i<w;i++){
				for(int e=0;e<h;e++){
					for(int layer=0;layer<this.mapdata.length;layer++){
						newmapdata[layer][i][e]="";
					}
					if(i<this.width && e<this.height){
						newmap[i][e]=this.map[i][e];
					}else{
						newmap[i][e]=-1;
					}
					
				}
			}
			//copying old data layers into new datalayers 
			for(int i=0;i<w;i++){
				for(int e=0;e<h;e++){
					for(int layer=0;layer<this.mapdata.length;layer++){
						if(layer<mapdata.length &&i<mapdata[layer].length && e<mapdata[layer][i].length && i<w && e<h){
							newmapdata[layer][i][e]=this.mapdata[layer][i][e];
						}
					}
				}
			}
		this.map=newmap;
		this.mapdata=newmapdata;
		this.width=w;
		this.height=h;
		System.out.println(this);

	}

	public int[][] getmap() {
		return map;
	}

	private static <T> T[] append(T[] arr, T element) {
		int N = arr.length;
		arr = Arrays.copyOf(arr, N + 1);
		arr[N] = element;
		return arr;
	}

	public void setTile(int t, int x, int y) {
		if (t > tiles.length || t < -1) {
			throw new IllegalArgumentException("tile number invalid");
		} else {
			map[y][x] = t;
		}

	}

	public int newDataLayer() {

		String[][] newlayer = new String[width][height];
		for (int i = 0; i < width; i++) {
			for (int e = 0; e < height; e++) {
				newlayer[i][e] = "";
			}
		}
		append(mapdata, newlayer);

		return mapdata.length;

	}

	public void setTile(tile t, int x, int y) {
		boolean inSet = false;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] == t) {
				setTile(i, x, y);
				inSet = true;
				break;
			}
		}
		if (!inSet) {
			tiles = append(tiles, t);
			setTile(tiles.length - 1, x, y);
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

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		j.setInt("width", map.length);
		j.setInt("height", map[0].length);
		j.setInt("tileWidth", tileWidth);
		j.setInt("tileHeight", tileHeight);
		JSONArray tileArray = new JSONArray();
		for (tile t : tiles) {
			tileArray.append(t.filePath);
		}
		j.setJSONArray("tiles", tileArray);

		JSONArray mapJson = new JSONArray();

		for (int[] row : map) {
			JSONArray mapRowJson = new JSONArray();
			for (int cell : row) {
				mapRowJson.append(cell);
			}
			mapJson.append(mapRowJson);
		}
		j.setJSONArray("map", mapJson);

		JSONArray mapDataJson = new JSONArray();

		for (String[][] layer : mapdata) {
			JSONArray mapDataLayer = new JSONArray();
			for (String[] row : layer) {
				JSONArray mapDataRow = new JSONArray();
				for (String cell : row) {
					mapDataRow.append(cell);
				}
				mapDataLayer.append(mapDataRow);
			}
			mapDataJson.append(mapDataLayer);
		}
		j.setJSONArray("mapdata", mapDataJson);

		return j;
	}

	public String toJsonString() {
		return toJson().toString();
	}

}
