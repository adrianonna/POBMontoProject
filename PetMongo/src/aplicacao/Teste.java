package aplicacao;
import java.net.UnknownHostException;
import java.security.Provider.Service;
/*
 * IFPB - POB - Fausto Ayres
 * Aplicação JPA + Mongodb
 */
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import modelo.Animal;
import modelo.Atendimento;
import modelo.Cliente;
import modelo.Produto;
import modelo.Raca;
import modelo.Servico;

public class Teste {
	private EntityManager manager;

	public Teste() {
		abrir();
		cadastrar();
		//atualizar();
		//excluir();
//		listar();
//		consultar();
		fechar();

		System.out.println("fim da aplicacao");
	}

	public  void abrir() {
		// obs: lembrar de abrir antes o servidor (mongod)
		manager = Persistence.createEntityManagerFactory("mongodb").createEntityManager();

		//apagar o banco para criar novamente
		//		manager.getTransaction().begin();
		//		try {
		//			MongoClient mongoClient = new MongoClient("localhost",27017);
		//			DB db = mongoClient.getDB("bdpedido");
		//			db.dropDatabase();
		//		} catch (Exception e) {e.printStackTrace();}
		//		manager.getTransaction().commit();

	}
	public  void fechar() {
		if (manager != null) {
			manager.close();
		}
	}

	public  void cadastrar() {
		Cliente c;
		Animal a;
		Produto p;
		Raca r;
		Servico s;
		Atendimento at;
		manager.getTransaction().begin();
		c = new Cliente("Renatha Victor", "(83) 98800-0000");
		r = new Raca("rottweiler", "grande", "cachorro");
		a = new Animal("Zoe", r);
		c.setAnimal(a);
		manager.persist(c);
		a.setCliente(c);
		manager.persist(a);
		
		p = new Produto("bravecto", 150.0);
		s = new Servico("banho e tosa menor", 48.0);
//		at = new Atendimento("Funcionario1", a);
		
		manager.persist(r);
		
		manager.persist(p);
		manager.persist(s);
		manager.getTransaction().commit();
		
		
//		Pedido pedido;
//		//criar pedido 1
//		manager.getTransaction().begin();
//		pedido = new Pedido("joao");
//		pedido.adicionar(new Item(100, 1.0, "tv", pedido));
//		pedido.adicionar(new Item(100, 2.0, "geladeira", pedido));
//		manager.persist(pedido);
//		manager.getTransaction().commit();
//
//		//criar pedido 2
//		manager.getTransaction().begin();
//		pedido = new Pedido("maria");
//		pedido.adicionar(new Item(100, 10.1, "relogio", pedido));
//		pedido.adicionar(new Item(100, 20.0, "computador", pedido));
//		manager.persist(pedido);
//		manager.getTransaction().commit();
//
//		//criar pedido 3
//		manager.getTransaction().begin();
//		pedido = new Pedido("jose");
//		pedido.adicionar(new Item(100, 40.1, "fogao", pedido));
//		manager.persist(pedido);
//		manager.getTransaction().commit();
//		System.out.println("\ncadastrou!\n");
//	}
//
//
//	public  void atualizar(){
//		Pedido pedido;
//		//atualizar pedido de maria
//		manager.getTransaction().begin();
//		pedido = localizar("maria"); 
//		pedido.getItens().get(0).setPreco(99);
//		pedido.adicionar(new Item(100, 30.0, "radio", pedido));
//		manager.merge(pedido);
//		manager.getTransaction().commit();
//		System.out.println("\natualizou!\n");
//
//	}
//
//	public void excluir(){
//		Pedido pedido;
//		//remover pedido 2
//		manager.getTransaction().begin();
//		pedido = localizar("jose"); 
//		manager.remove(pedido);
//		manager.getTransaction().commit();
//		System.out.println("\nexcluiu!\n");
//	}
//
//	public Pedido localizar(String nome){
//		Pedido pedido;
//		Query q;
//
//		//localizar pedido maria
//		q = manager.createQuery("SELECT p FROM Pedido p WHERE p.cliente = '"+nome+"'");
//		pedido = (Pedido)q.getSingleResult();
//		System.out.println("\nlocalizando pedido(jpql) de "+nome+":\n"+pedido);
//
//		/**
//		 * native MongoDB query (o mesmo comando usado no Mongo shell)
//		 * Observar que o nome dos campos são maiusculos.
//		 */
//		pedido = (Pedido)manager
//				.createNativeQuery("db.PEDIDO.findOne({CLIENTE : \""+nome+"\"})", Pedido.class)
//				.getSingleResult();
//		System.out.println("\nlocalizando pedido(nativo) de "+nome+":\n"+pedido);
//
//		return pedido;
//	}
//
//	public void listar(){
//		Query q;
//		q = manager.createQuery("SELECT p FROM Pedido p ");
//		List<Pedido> pedidos = q.getResultList();
//		System.out.println("\n**********listagem dos pedidos:");
//		for(Pedido p : pedidos)
//			System.out.println(p);
//
//		q = manager.createQuery("SELECT i FROM Item i ");
//		List<Item> itens = q.getResultList();
//		System.out.println("\n===========listagem dos itens:");
//		for(Item it : itens)
//			System.out.println(it);
//	}
//
//	public void consultar() {
//		Query q;
//		q = manager.createQuery("SELECT i FROM Item i where i.preco > 10");
//		List<Item> itens = q.getResultList();
//		System.out.println("\nconsulta de itens com preco > 10:");
//		for(Item it : itens)
//			System.out.println(it);

		/**
		 * Native queries with nested paths. - precisa testar
		 */
		//				List<Pedido> pedidos = (List<Pedido>)manager
		//						.createNativeQuery("db.PEDIDO.findMany({ITENS.QUANTIDADE: \"3\"})", Pedido.class)
		//						.getResultList();
		//				System.out.println(pedidos);
	}



	public static void main(String[] args) {
		new Teste();
	}
}