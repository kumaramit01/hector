package me.prettyprint.cassandra.model;

import me.prettyprint.cassandra.utils.Assert;
import me.prettyprint.hector.api.ConsistencyLevelPolicy;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.Serializer;
import me.prettyprint.hector.api.query.Query;

/**
 * 
 * @author patricioe (Patricio Echague - patricio@datastax.com)
 *
 * @param <K> Key type
 * @param <N> column name type
 * @param <T> return type
 */
public abstract class AbstractBasicQuery<K, N, T> implements Query<T> {

  protected final ExecutingKeyspace keyspace;
  protected String columnFamilyName;
  protected Serializer<K> keySerializer;
  protected Serializer<N> columnNameSerializer;
  protected ConsistencyLevelPolicy consistencyLevelPolicy;
  // add: FailoverPolicy, ConsistencyLevelPolicy, Credentials?

  protected AbstractBasicQuery(Keyspace k, Serializer<K> keySerializer,
      Serializer<N> nameSerializer) {
    Assert.noneNull(k, keySerializer, nameSerializer);
    keyspace = (ExecutingKeyspace) k;
    this.keySerializer = keySerializer;
    this.columnNameSerializer = nameSerializer;
  }

  protected AbstractBasicQuery(Keyspace k, Serializer<K> keySerializer,
                                 Serializer<N> nameSerializer, ConsistencyLevelPolicy consistencyLevelPolicy) {
      Assert.noneNull(k, keySerializer, nameSerializer, consistencyLevelPolicy);
      keyspace = (ExecutingKeyspace) k;
      this.keySerializer = keySerializer;
      this.columnNameSerializer = nameSerializer;
      this.consistencyLevelPolicy = consistencyLevelPolicy;
  }

  public Query<T> setColumnFamily(String cf) {
    this.columnFamilyName = cf;
    return this;
  }

  public Serializer<K> getKeySerializer() {
    return keySerializer;
  }

  public AbstractBasicQuery<K, N, T> setKeySerializer(Serializer<K> keySerializer) {
    this.keySerializer = keySerializer;
    return this;
  }

  public Serializer<N> getColumnNameSerializer() {
    return columnNameSerializer;
  }

  public AbstractBasicQuery<K, N, T> setColumnNameSerializer(Serializer<N> columnNameSerializer) {
    this.columnNameSerializer = columnNameSerializer;
    return this;
  }

  public AbstractBasicQuery<K, N, T> setConsistencyLevelPolicy(ConsistencyLevelPolicy consistencyLevelPolicy){
      this.consistencyLevelPolicy =  consistencyLevelPolicy;
      return this;
  }

  public ConsistencyLevelPolicy getConsistencyLevelPolicy(){
      return this.consistencyLevelPolicy;
  }

}
