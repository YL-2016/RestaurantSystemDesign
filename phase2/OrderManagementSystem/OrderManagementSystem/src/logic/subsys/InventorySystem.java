package logic.subsys;

import java.util.HashMap;
import java.util.Map;

import utils.IOUtils;
import utils.LogUtils;

public class InventorySystem {
	private Map<String, Integer> ingredientMap;

	public InventorySystem() {
		super();
		initialze();
	}

	private void initialze() {
		this.ingredientMap = IOUtils.getInstance().loadInventory();
	}

	public Map<String, Integer> getIngredientMap() {
		return ingredientMap;
	}

	public boolean isEnough(Map<String, Integer> needsMap) {
		for (Map.Entry<String, Integer> entry : needsMap.entrySet()) {
			if (ingredientMap.get(entry.getKey()) < entry.getValue()) {
				return false;
			}
		}

		return true;
	}

	public void reduce(Map<String, Integer> needsMap) {
		LogUtils.getInstance().log("Inventory reduce start");
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : needsMap.entrySet()) {
			String name = entry.getKey();
			int value = ingredientMap.get(name) - entry.getValue();
			ingredientMap.put(name, value);
			resultMap.put(name, value);
			LogUtils.getInstance().log(
					"Ingredient " + name + " is reduced to " + value);
		}
		IOUtils.getInstance().updateInventory(resultMap);
		LogUtils.getInstance().log("Inventory reduce finished");
	}

	public void add(Map<String, Integer> needsMap) {
		LogUtils.getInstance().log("Inventory add start");
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : needsMap.entrySet()) {
			String name = entry.getKey();
			int value = ingredientMap.get(name) + entry.getValue();
			ingredientMap.put(name, value);
			resultMap.put(name, value);
			LogUtils.getInstance().log(
					"Ingredient " + name + " is added to " + value);
		}
		IOUtils.getInstance().updateInventory(resultMap);
		LogUtils.getInstance().log("Inventory add finished");
	}

	public void update(Map<String, Integer> needsMap) {
		LogUtils.getInstance().log("Inventory update start");
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : needsMap.entrySet()) {
			String name = entry.getKey();
			int value = entry.getValue();
			ingredientMap.put(name, value);
			resultMap.put(name, value);
			LogUtils.getInstance().log(
					"Ingredient " + name + " is updated to " + value);
		}
		IOUtils.getInstance().updateInventory(resultMap);
		LogUtils.getInstance().log("Inventory update finished");
	}

}
