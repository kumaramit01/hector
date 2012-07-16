package me.prettyprint.cassandra.model;

import me.prettyprint.cassandra.utils.Assert;
import me.prettyprint.hector.api.ConsistencyLevelPolicy;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.Serializer;
import me.prettyprint.hector.api.query.Query;


public abstract class AbstractQuery<K, N, V, T> extends AbstractBasicQuery<K, N, T> implements Query<T> {

  protected Serializer<V> valueSerializer;
  protected ConsistencyLevelPolicy consistencyLevelPolicy;
  // add: FailoverPolicy, ConsistencyLevelPolicy, Credentials?

  /*package*/ AbstractQuery(Keyspace k, Serializer<K> keySerializer,
      Serializer<N> nameSerializer, Serializer<V> valueSerializer) {
	  super(k, keySerializer, nameSerializer);
    Assert.noneNull(valueSerializer);
    this.valueSerializer = valueSerializer;
  }

    AbstractQuery(Keyspace k, Serializer<K> keySerializer,
                  Serializer<N> nameSerializer, Serializer<V> valueSerializer,ConsistencyLevelPolicy consistencyLevelPolicy) {
      super(k, keySerializer, nameSerializer,consistencyLevelPolicy);
      Assert.noneNull(valueSerializer);
      this.valueSerializer = valueSerializer;
      this.consistencyLevelPolicy = consistencyLevelPolicy;
    }

  public Serializer<V> getValueSerializer() {
    return valueSerializer;
  }

  public void setValueSerializer(Serializer<V> valueSerializer) {
    this.valueSerializer = valueSerializer;
  }

  public ConsistencyLevelPolicy getConsistencyLevel() {
        return consistencyLevelPolicy;
  }

  public void setValueSerializer(ConsistencyLevelPolicy consistencyLevelPolicy) {
       this.consistencyLevelPolicy = consistencyLevelPolicy;
  }
}
