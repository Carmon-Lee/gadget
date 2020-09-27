package datastructure;

/**
 * @author liguang
 * @version StrategyRuleMatcher.java, v 0.1 2020年09月08日 9:24 下午
 */

import java.util.*;
import java.util.function.Function;

/**
 * @author liguang
 * @version StrategyRuleMatcher.java, v 0.1 2020年09月08日 18:49
 */
public class StrategyRuleMatcher {

    public static void main(String[] args) {
        List<Rule> rules = rules();
        StrategyRuleMatcher matcher = new MatcherBuilder()
                .rules(rules)
                .valueGetters(Rule::getUid, Rule::getWid, Rule::getSid)
                .build();

        for (Rule rule : rules) {
            List<Rule> matchRules = matcher.findMatchRules(rule);
            System.out.println(matchRules.size()==1 && matchRules.get(0) ==rule);
        }
        System.out.println();
    }


    static class MatcherBuilder {
        private List<Rule> rules;
        private Function<Rule, Object>[] valueGetters;

        public MatcherBuilder rules(List<Rule> rules) {
            this.rules = rules;
            return this;
        }

        @SuppressWarnings("unchecked")
        public MatcherBuilder valueGetters(Function<Rule, Object>... valueGetters) {
            this.valueGetters = valueGetters;
            return this;
        }

        public StrategyRuleMatcher build() {
            if (valueGetters == null || valueGetters.length == 0) {
                return null;
            } else {
                LinkedList<Function<Rule, Object>> list = new LinkedList<>(Arrays.asList(valueGetters));

                LevelNode headNode = new LevelNode();
                headNode.valueGetter = list.get(0);
                headNode.nextGetterIndex = 1;
                headNode.ruleValueGetters = list;
                headNode.rules = rules;
                if (list.size() > 1) {
                    headNode.recursiveBuild();
                }
                return new StrategyRuleMatcher(headNode);
            }
        }
    }


    private LevelNode headNode;

    public StrategyRuleMatcher(LevelNode headNode) {
        this.headNode = headNode;
    }

    List<Rule> findMatchRules(Rule rule) {
        return headNode.findRules(rule);
    }

    public static final List<Rule> EMPTY = Collections.unmodifiableList(new ArrayList<>());

    static class LevelNode {
        // 当前节点的后续
        private int nextGetterIndex;
        private List<Function<Rule, Object>> ruleValueGetters;
        // 原始数据：转换函数
        private Function<Rule, Object> valueGetter;
        // 该节点数据
        List<Rule> rules;

        // 该节点映射的下层节点
        private Map<Object, LevelNode> mapping;

        public List<Rule> findRules(Rule targetRule) {
            // 没有下层映射关系，说明已经是最后一层
            if (mapping == null) {
                return rules;
            }

            Object value = valueGetter.apply(targetRule);
            LevelNode levelNode = mapping.get(value);

            return levelNode == null ? EMPTY : levelNode.findRules(targetRule);
        }

        private void recursiveBuild() {
            if (nextGetterIndex > ruleValueGetters.size()) {
                return;
            }
            Function<Rule, Object> nextValueGetter = null;
            if (nextGetterIndex<ruleValueGetters.size()) {
                nextValueGetter = ruleValueGetters.get(nextGetterIndex);
            }

            Map<Object, List<Rule>> collect = groupByNull(rules, valueGetter);
            mapping = new HashMap<>();
            for (Map.Entry<Object, List<Rule>> entry : collect.entrySet()) {
                LevelNode levelNode = new LevelNode();
                levelNode.rules = entry.getValue();
                levelNode.valueGetter = nextValueGetter;
                levelNode.ruleValueGetters = ruleValueGetters;
                levelNode.nextGetterIndex = nextGetterIndex + 1;
                mapping.put(entry.getKey(), levelNode);
                if (nextGetterIndex < ruleValueGetters.size()) {
                    levelNode.recursiveBuild();
                }
            }
        }
    }


    public static Map<Object, List<Rule>> groupByNull(List<Rule> rules, Function<Rule, Object> function) {
        Map<Object, List<Rule>> res = new HashMap<>();
        for (Rule rule : rules) {
            Object apply = function.apply(rule);
            res.putIfAbsent(apply, new ArrayList<>());
            res.get(apply).add(rule);
        }
        return res;
    }


    private static List<Rule> rules() {
        List<Rule> rules = new ArrayList<>();
        Rule rule1 = new Rule("uid:1", "wId:1", "stationId:1");
        Rule rule2 = new Rule("uid:1", "wId:2", "stationId:2");
        Rule rule3 = new Rule("uid:1", "wId:2", "stationId:3");
        Rule rule4 = new Rule("uid:1", null, "stationId:3");
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);
        rules.add(rule4);
        return rules;
    }
}
