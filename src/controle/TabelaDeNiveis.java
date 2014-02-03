package controle;

import java.util.*;
import modelo.*;

public class TabelaDeNiveis
{
	// atributos
	private Map<Material, Short> niveisLLC;

	public TabelaDeNiveis()
	{
		niveisLLC = new TreeMap<Material, Short>(new Comparator<Material>()
			{
				public int compare(Material m1, Material m2)
				{
					return m1.getNome().compareTo(m2.getNome());
				}
			});
	}

	// métodos get/set
	public void setNivel(Material material, short nivel)
	{
		if (niveisLLC.containsKey(material)) if (niveisLLC.get(material) >= nivel) return;
		niveisLLC.put(material, nivel);
	}

	public Short getNivel(Material material)
	{
		return null;
	}

	public String toString()
	{
		return niveisLLC.toString();
	}

	public Set<Material> getMateriais()
	{
		return niveisLLC.keySet();
	}

	// enfileira a tabela para uso no cálculo de programação
	public Queue<Material> enqueue()
	{
		Queue<Material> fila = new LinkedList<Material>();
		short menorNivel = 0;
		boolean achou = true;

		while (achou)
		{
			achou = false;
			for (Map.Entry<Material, Short> entry : niveisLLC.entrySet())
			{
				if (entry.getValue() == menorNivel)
				{
					fila.add(entry.getKey());
					achou = true;
				}
			}
			menorNivel++;
		}

		return fila;
	}

}
