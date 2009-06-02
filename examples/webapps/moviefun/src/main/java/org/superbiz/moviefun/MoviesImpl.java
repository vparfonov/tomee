/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.superbiz.moviefun;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.jws.WebService;
import javax.annotation.Resource;
import java.util.List;

@Stateless(name = "Movies")
@WebService(portName = "MoviesPort",
        serviceName = "MoviesWebService",
        targetNamespace = "http://superbiz.org/wsdl")
public class MoviesImpl implements Movies {

    @PersistenceContext(unitName = "movie-unit")
    private EntityManager entityManager;

    public void addMovie(Movie movie) throws Exception {
        entityManager.persist(movie);
    }

    public void deleteMovie(Movie movie) throws Exception {
        entityManager.remove(movie);
    }

    public void deleteMovieId(long id) throws Exception {
        Movie movie = entityManager.find(Movie.class, id);
        entityManager.remove(movie);
    }

    public List<Movie> getMovies() throws Exception {
        Query query = entityManager.createQuery("SELECT m from Movie as m");
        return query.getResultList();
    }

    public List<Movie> findByTitle(String title) throws Exception {
        Query query = entityManager.createQuery("SELECT m from Movie as m where m.title = ?1");
        query.setParameter(1, title);
        return query.getResultList();
    }

    public List<Movie> findByGenre(String genre) throws Exception {
        Query query = entityManager.createQuery("SELECT m from Movie as m where m.genre = ?1");
        query.setParameter(1, genre);
        return query.getResultList();
    }

    public List<Movie> findByDirector(String director) throws Exception {
        Query query = entityManager.createQuery("SELECT m from Movie as m where m.director = ?1");
        query.setParameter(1, director);
        return query.getResultList();
    }

}