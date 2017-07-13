package br.com.cygnus.exemplos.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cygnus.exemplos.persistence.model.Livro;

/**
 * Repositorio onde sao armazenados os {@link Livro}. Atualmente utilizando banco de dados NoSQL (MongoDB).
 * 
 * @see MongoRepository
 */
public interface LivroRepository extends MongoRepository<Livro, String> {

}
