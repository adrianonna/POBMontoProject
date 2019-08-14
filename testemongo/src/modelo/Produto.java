package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity 
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Produto implements Serializable {
	
	@Id		
	@GeneratedValue
	@Field(name="_id")
	private String id;
	
	private String nome;
	private double preco;
	
	@ManyToMany
	private List<Atendimento> atendimentos;
	
	public Produto() {
		
	}
	
	public Produto(String nome, double preco){
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Atendimento atendimento) {
		this.atendimentos.add(atendimento);
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", preco=" + preco + "]";
	}
	
	
}
