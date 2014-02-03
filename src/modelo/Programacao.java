package modelo;

import java.util.*;

public class Programacao
{
	public static final short SEMANAS = -100;
	public static final short DIAS = -101;

	private Long id;
	private String descricao;
	private Short unidadeDeTempo;
	private Short tempoInicial;
	private Short tempoFinal;

	private Set ols;

	public Programacao()
	{
	}

	public Programacao(String descricao, Short unidadeDeTempo,
			Short tempoInicial, Short tempoFinal, Set ols)
	{
		if (tempoInicial > tempoFinal)
			throw new IllegalArgumentException("Tempo inicial é maior que o tempo final.");
		id = (long) 0;
		this.descricao = descricao;
		this.unidadeDeTempo = unidadeDeTempo;
		this.tempoInicial = tempoInicial;
		this.tempoFinal = tempoFinal;
		this.ols = ols;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Set getOls()
	{
		return ols;
	}

	public void setOls(Set ols)
	{
		this.ols = ols;
	}

	public Short getTempoFinal()
	{
		return tempoFinal;
	}

	public void setTempoFinal(Short tempoFinal)
	{
		if (tempoInicial != null && tempoFinal != null && tempoInicial > tempoFinal)
			throw new IllegalArgumentException("Tempo final é menor que o tempo inicial.");
		this.tempoFinal = tempoFinal;
	}

	public Short getTempoInicial()
	{
		return tempoInicial;
	}

	public void setTempoInicial(Short tempoInicial)
	{
		if (tempoInicial != null && tempoFinal != null && tempoInicial > tempoFinal)
			throw new IllegalArgumentException("Tempo inicial é maior que o tempo final.");
		this.tempoInicial = tempoInicial;
	}

	public Short getUnidadeDeTempo()
	{
		return unidadeDeTempo;
	}

	public void setUnidadeDeTempo(Short unidadeDeTempo)
	{
		this.unidadeDeTempo = unidadeDeTempo;
	}

}
