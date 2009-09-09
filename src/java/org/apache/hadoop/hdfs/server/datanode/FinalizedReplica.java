/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hdfs.server.datanode;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.hdfs.protocol.Block;
import org.apache.hadoop.hdfs.server.common.HdfsConstants.ReplicaState;
import org.apache.hadoop.hdfs.server.datanode.FSDataset.FSVolume;

/**
 * This class describes a replica that has been finalized.
 */
class FinalizedReplica extends ReplicaInfo {
  private boolean detached;      // copy-on-write done for block

  /**
   * Constructor
   * @param blockId block id
   * @param len replica length
   * @param genStamp replica generation stamp
   * @param vol volume where replica is located
   * @param dir directory path where block and meta files are located
   */
  FinalizedReplica(long blockId, long len, long genStamp,
      FSVolume vol, File dir) {
    super(blockId, len, genStamp, vol, dir);
  }
  
  /**
   * Constructor
   * @param block a block
   * @param vol volume where replica is located
   * @param dir directory path where block and meta files are located
   */
  FinalizedReplica(Block block, FSVolume vol, File dir) {
    super(block, vol, dir);
  }
  
  @Override  // ReplicaInfo
  public ReplicaState getState() {
    return ReplicaState.FINALIZED;
  }
  
  @Override // ReplicaInfo
  boolean isDetached() {
    return detached;
  }

  @Override  // ReplicaInfo
  void setDetached() {
    detached = true;
  }
  
  @Override  // ReplicaInfo
  long getVisibleLen() throws IOException {
    return getNumBytes();       // all bytes are visible
  }
  
  @Override  // Object
  public boolean equals(Object o) {
    return super.equals(o);
  }
  
  @Override  // Object
  public int hashCode() {
    return super.hashCode();
  }
}