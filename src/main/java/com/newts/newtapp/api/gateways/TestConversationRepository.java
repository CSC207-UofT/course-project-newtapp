package com.newts.newtapp.api.gateways;

import com.newts.newtapp.entities.Conversation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A ConversationRepository implemented for testing purposes (Warnings are due to the fact that SpringBoot
 * usually implements this)
 */
public class TestConversationRepository implements ConversationRepository{
    /**
     * An arraylist of conversations representing a repository of conversations
     */
    private ArrayList<Conversation> Conversations;

    /**
     * Create a new TestConversationRepository.
     */
    public TestConversationRepository(){
        this.Conversations = new ArrayList<Conversation>();
    }

    /**
     * Add a conversation to the repository (Warnings are just due to SpringBoot)
     * @param c a conversation to be added
     * @return null
     */
    @Override
    public Object save(Conversation c){
        // In the actual save function, it replaces the original c with the new c if it exists,
        // for testing purposes, we can also just clear it completely since we deal with one
        // conversation at a time.
        this.Conversations.clear();
        this.Conversations.add(c);
        return null;
    }

    /**
     * Find the conversation with the id
     * @param id the id to search for
     * @return the conversation with the id
     */
    public Conversation findById(int id){
        for (Conversation c: this.Conversations){
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Conversation> findAll() {
        return null;
    }

    @Override
    public List<Conversation> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Conversation> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Conversation> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Conversation entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Conversation> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Conversation> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Conversation> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Conversation> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Conversation> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Conversation> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Conversation getOne(Integer integer) {
        return null;
    }

    @Override
    public Conversation getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Conversation> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Conversation> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Conversation> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Conversation> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Conversation> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Conversation> boolean exists(Example<S> example) {
        return false;
    }
}
