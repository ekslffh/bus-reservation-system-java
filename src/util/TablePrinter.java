package util;

import java.util.List;

public class TablePrinter<T> {

	private List<T> data;
	private int[] columnWidths;

	public TablePrinter(List<T> data) {
		this.data = data;
		this.columnWidths = getColumnWidths(data);
	}

	// �� ���� �ִ� ���� ���
	private int[] getColumnWidths(List<T> data) {
		int[] columnWidths = new int[data.get(0).getClass().getDeclaredFields().length];
		for (T item : data) {
			int columnIndex = 0;
			for (java.lang.reflect.Field field : item.getClass().getDeclaredFields()) {
				String value = null;
				try {
					field.setAccessible(true);
					value = String.valueOf(field.get(item));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				if (value != null) {
					int maxColumnWidth = Math.max(columnWidths[columnIndex], value.length());
					int maxFieldNameWidth = Math.max(columnWidths[columnIndex], field.getName().length());
					columnWidths[columnIndex] = Math.max(maxColumnWidth, maxFieldNameWidth);
				} else {
					int maxFieldNameWidth = Math.max(columnWidths[columnIndex], field.getName().length());
					columnWidths[columnIndex] = maxFieldNameWidth;
				}
				columnIndex++;
			}
		}
		return columnWidths;
	}

	// ���м� ���
	private void printSeparator() {
		System.out.print("+");
		for (int i = 0; i < columnWidths.length; i++) {
			System.out.print(repeat("-", columnWidths[i] + 2) + "+");
		}
		System.out.println();
	}

	// ���ڿ��� �ݺ��Ͽ� ����
	private String repeat(String s, int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(s);
		}
		return sb.toString();
	}

	// ������ ���
	public void printTable() {
		// ���м� ���
		printSeparator();

		// ��� ���
		System.out.print("| ");
		int columnIndex = 0;
		for (java.lang.reflect.Field field : data.get(0).getClass().getDeclaredFields()) {
			System.out.print(String.format("%-" + columnWidths[columnIndex] + "s | ", field.getName()));
			columnIndex++;
		}
		System.out.println();

		// ���м� ���
		printSeparator();

		// ������ ���
		for (T item : data) {
			System.out.print("| ");
			columnIndex = 0;
			for (java.lang.reflect.Field field : item.getClass().getDeclaredFields()) {
				String value = null;
				try {
					field.setAccessible(true);
					value = String.valueOf(field.get(item));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				if (value != null) {
					System.out.print(String.format("%-" + columnWidths[columnIndex] + "s | ", value));
				} else {
					System.out.print(String.format("%-" + columnWidths[columnIndex] + "s | ", ""));
				}
				columnIndex++;
			}
			System.out.println();
		}

		// ���м� ���
		printSeparator();
	}
}
