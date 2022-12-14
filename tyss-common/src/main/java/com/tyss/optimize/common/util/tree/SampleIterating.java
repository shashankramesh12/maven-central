package com.tyss.optimize.common.util.tree;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SampleIterating {

    public static void main(String[] args) {
        TreeNode<String> treeRoot = SampleTreeData.getSet1();
        for (TreeNode<String> node : treeRoot) {
            String indent = createIndent(node.getLevel());
            log.info(indent + node.data);
        }
    }

    private static String createIndent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

}

