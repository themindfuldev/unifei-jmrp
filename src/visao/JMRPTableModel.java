/*
 * JMRPTableModel.java
 * 
 * Created on 19 de Novembro de 2006, 09:58
 * 
 * To change this template, choose Tools | Template Manager and open the
 * template in the editor.
 */

package visao;

import javax.swing.table.*;
import java.util.*;

/**
 * 
 * @author Tiago
 */
@SuppressWarnings("serial")
public class JMRPTableModel extends AbstractTableModel
{
	// variáveis
	private String[] columnNames;
	private ArrayList<ArrayList<Object>> data;
	private boolean relacaoFixa;

	public ArrayList<ArrayList<Object>> getData()
	{
		return data;
	}

	JMRPTableModel(String[] columnNames, ArrayList<ArrayList<Object>> data,
			boolean relacaoFixa)
	{
		this.columnNames = columnNames;
		this.data = data;
		this.relacaoFixa = relacaoFixa;
	}

	// métodos herdados
	public boolean isCellEditable(int row, int col)
	{
		if (relacaoFixa == false) return false;
		if (col == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public int getColumnCount()
	{
		return columnNames.length;
	}

	public int getRowCount()
	{
		return data.size();
	}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	public Object getValueAt(int row, int col)
	{
		return data.get(row).get(col);
	}

	public void setValueAt(Object value, int row, int col)
	{
		data.get(row).set(col, value);
		fireTableCellUpdated(row, col);
	}

	// Método para substituir os dados
	public void replaceData(ArrayList<ArrayList<Object>> newData)
	{
		data = newData;
		fireTableStructureChanged();
	}

	public void addValues(ArrayList<Object> linha)
	{
		data.add(linha);
		fireTableRowsInserted(data.size(), data.size());
	}

	public void removeRow(int linhaAtual)
	{
		data.remove(linhaAtual);
		fireTableRowsDeleted(linhaAtual, linhaAtual);
	}

	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}

}
