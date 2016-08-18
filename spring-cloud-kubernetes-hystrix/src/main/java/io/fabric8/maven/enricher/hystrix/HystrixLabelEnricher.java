/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.maven.enricher.hystrix;

import io.fabric8.maven.enricher.api.BaseEnricher;
import io.fabric8.maven.enricher.api.EnricherContext;
import io.fabric8.maven.enricher.api.Kind;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ceposta 
 * <a href="http://christianposta.com/blog>http://christianposta.com/blog</a>.
 */
public class HystrixLabelEnricher extends BaseEnricher {

    public HystrixLabelEnricher(EnricherContext buildContext, String name) {
        super(buildContext, name);
    }

    public HystrixLabelEnricher(EnricherContext buildContext) {
        super(buildContext, "hystrix-labels");
    }

    @Override
    public Map<String, String> getLabels(Kind kind) {
        if (kind.isService()) {
            String enabled = getContext().getProject().getProperties().getProperty("hystrix.enabled", "true");
            String cluster = getContext().getProject().getProperties().getProperty("hystrix.cluster", "default");
            HashMap<String, String> labels = new HashMap<>();
            labels.put("hystrix.enabled", enabled);
            labels.put("hystrix.cluster", cluster);
            return labels;
        }

        return null;
    }
}
