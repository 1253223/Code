package cn.lastwhisper.datastructure.linkedlist;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 单链表的功能实现：增删改查
 *  有序插入、有序合并、删除重复节点
 * @author cn.lastwhisper
 */
public class SingleLinkedListDemo {
    static SingleLinkedList singleLinkedList = new SingleLinkedList();
    static HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
    static HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
    static HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
    static HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

    static {
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
    }

    public static void main(String[] args) {

        System.out.println("===============查看链表所有节点================");
        singleLinkedList.list();
        System.out.println("===============删除链表某节点==================");
        singleLinkedList.delete(2);
        System.out.println("===============查看链表所有节点================");
        singleLinkedList.list();
        System.out.println("================更新链表节点,4=================");
        singleLinkedList.update(new HeroNode(4, "林冲", "豹子头~~~"));
        System.out.println("===============查看链表所有节点================");
        singleLinkedList.list();
        System.out.println("===============按序插入节点,2,3================");
        singleLinkedList.addByNoOrder(new HeroNode(2, "卢俊义回来了~~~", "玉麒麟"));
        singleLinkedList.addByNoOrder(new HeroNode(3, "吴用", "智多星"));
        //singleLinkedList.addByNoOrderIsEqual(new HeroNode(2, "卢俊义回来了~~~", "玉麒麟"));
        //singleLinkedList.addByNoOrderIsEqual(new HeroNode(3, "吴用", "智多星"));
        System.out.println("===============查看链表所有节点================");
        singleLinkedList.list();
        System.out.println("===============按序插入节点,8================");
        singleLinkedList.addByNoOrder(new HeroNode(8, "武松", "行者"));
        System.out.println("===============查看链表所有节点================");
        singleLinkedList.list();
        System.out.println("===============按序插入节点,7================");
        singleLinkedList.addByNoOrder(new HeroNode(7, "入云龙", "公孙胜"));
        System.out.println("===============查看链表所有节点================");
        singleLinkedList.list();
        System.out.println("---------------获取链表长度,不包括头节点-----------");
        System.out.println(singleLinkedList.size());
        System.out.println("---------------获取链表倒数第2个元素-----------");
        System.out.println(singleLinkedList.findLastIndexNode(2));
        System.out.println("---------------反转单链表--------------");
        singleLinkedList.reverse();
        singleLinkedList.list();
        System.out.println("---------------反转单链表--------------");
        singleLinkedList.reverse();
        System.out.println("---------------从尾到头打印单链表---------------");
        singleLinkedList.reverseList();

        System.out.println("---------------合并有序单链表---------------");
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        HeroNode hero21 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero23 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode hero25 = new HeroNode(6, "吴用", "智多星");
        HeroNode hero27 = new HeroNode(7, "林冲", "豹子头");
        singleLinkedList2.add(hero21);
        singleLinkedList2.add(hero23);
        singleLinkedList2.add(hero25);
        singleLinkedList2.add(hero27);
        System.out.println("---------------查看二号链表所有节点---------------");
        singleLinkedList2.list();
        SingleLinkedList singleLinkedList3 = new SingleLinkedList();
        HeroNode hero32 = new HeroNode(2, "柴进", "小旋风");
        HeroNode hero34 = new HeroNode(4, "鲁智深", "花和尚");
        HeroNode hero36 = new HeroNode(6, "石秀", "拼命三郎");
        HeroNode hero38 = new HeroNode(8, "秦明", "霹雳火");
        singleLinkedList3.add(hero32);
        singleLinkedList3.add(hero34);
        singleLinkedList3.add(hero36);
        singleLinkedList3.add(hero38);
        System.out.println("---------------查看三号链表所有节点---------------");
        singleLinkedList3.list();
        System.out.println("-----------------合并结束-------------------");
        singleLinkedList2.mergeOrderLinkedList(singleLinkedList3);
        System.out.println("-----------------查看二号链表所有节点-----------------");
        singleLinkedList2.list();
    }

    // 测试删除重复节点
    @Test
    public void testDeleteDuplicate() {
        HeroNode hero5 = new HeroNode(5, "宋江", "豹子头");
        singleLinkedList.add(hero5);
        singleLinkedList.deleteDuplicate();
        singleLinkedList.list();
    }

}

/**
 * 单链表
 *
 */
class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点
     *  找到单链表lastNode，lastNode.next=newNode
     */
    public void add(HeroNode heroNode) {
        HeroNode tempNode = head;
        // 找到最后一个节点
        while (true) {
            if (tempNode.next == null) {
                break;
            }
            tempNode = tempNode.next;
        }
        tempNode.next = heroNode;
    }

    /**
     * 根据排名插入到指定位置
     *  如果已存在该排名添加失败
     *
     * @param heroNode
     * @return void
     */
    public void addByNoOrder(HeroNode heroNode) {
        HeroNode tempNode = head;
        HeroNode preNode;
        boolean flag = true;
        while (true) {
            if (tempNode.next == null) {
                break;
            }
            preNode = tempNode;
            tempNode = tempNode.next;

            if (heroNode.no == tempNode.no) {
                flag = false;
                System.out.println("已存在排名，添加失败" + heroNode);
                break;
            }

            if (heroNode.no < tempNode.no) {
                // 1,3,4 heroNode.no=2 tempNode.next.no=3 2<3 可以添加
                // heroNode指向no=3节点
                heroNode.next = tempNode;
                // tempNode指向heroNode节点
                preNode.next = heroNode;
                flag = false;
                break;
            }
        }
        if (flag) {
            // 待插入节点no最大
            tempNode.next = heroNode;
        }
    }

    /**
     * 根据排名插入到指定位置
     *  可以存在相同编号的节点
     * @param heroNode
     * @return void
     */
    public void addByNoOrderIsEqual(HeroNode heroNode) {
        HeroNode tempNode = head;
        HeroNode preNode;
        boolean flag = true;
        while (true) {
            if (tempNode.next == null) {
                break;
            }
            preNode = tempNode;
            tempNode = tempNode.next;

            if (heroNode.no <= tempNode.no) {
                // 1,3,4 heroNode.no=2 tempNode.next.no=3 2<3 可以添加
                // heroNode指向no=3节点
                heroNode.next = tempNode;
                // tempNode指向heroNode节点
                preNode.next = heroNode;
                flag = false;
                break;
            }
        }
        if (flag) {
            // 待插入节点no最大
            tempNode.next = heroNode;
        }
    }

    /**
     * 展示所有节点，沿着头节点遍历
     *
     * @param
     * @return void
     */
    public void list() {
        HeroNode currNode = head.next;
        while (currNode != null) {
            System.out.printf("%d\t%s\t%s\t\n", currNode.no, currNode.name, currNode.nickName);
            currNode = currNode.next;
        }
    }


    /**
     * 删除链表中重复的元素
     */
    public void deleteDuplicate() {
        Set<String> set = new HashSet<>();
        HeroNode currNode = head.next;
        HeroNode preNode = null;
        while (currNode != null) {
            if (set.contains(currNode.name)) {
                preNode.next = currNode.next;
            } else {
                set.add(currNode.name);
                preNode = currNode;
            }
            currNode = currNode.next;
        }

    }

    /**
     * 根据no删除节点
     *
     * @param no
     * @return void
     */
    public void delete(int no) {
        // 1、沿着头节点tempNode遍历，找到node.no=no的前一个节点preNode
        HeroNode preNode = head;
        while (true) {
            if (preNode.next == null) {
                System.out.printf("未找到no==%d 节点\n", no);
                break;
            }
            // tempNode是待删除节点的前一个节点
            preNode = preNode.next;
            // 2. 找到待删除节点的前一个节点
            if (preNode.next.no == no) {
                preNode.next = preNode.next.next;
                System.out.printf("已删除no==%d 节点\n", no);
                break;
            }
        }
    }

    /**
     * 根据no更新节点
     *
     * @param updateNode
     * @return void
     */
    public void update(HeroNode updateNode) {
        HeroNode tempNode = head;
        while (true) {
            if (tempNode.next == null) {
                System.out.printf("未找到no==%d 节点\n", updateNode.no);
                break;
            }
            tempNode = tempNode.next;
            // 找到待更新节点
            if (tempNode.no == updateNode.no) {
                tempNode.name = updateNode.name;
                tempNode.nickName = updateNode.nickName;
                System.out.printf("已更新no==%d 节点\n", updateNode.no);
                break;
            }
        }
    }

    /**
     * 获取单链表的节点个数(不统计头节点)
     *
     * @param
     * @return int
     */
    public int size() {
        HeroNode currNode = head.next;
        int counter = 0;
        while (currNode != null) {
            counter++;
            currNode = currNode.next;
        }
        return counter;
    }

    /**
     * 获取单链表倒数第k个节点
     * 思路：倒数第k个等价于正数size-k+1个
     * 示例：7个元素，倒数第2个==正数第6个
     * @param k
     * @return cn.cn.lastwhisper.linkedlist.HeroNode
     */
    public HeroNode findLastIndexNode(int k) {
        int end = size() - k;
        if (end < 0) {
            throw new RuntimeException("不存在的倒数第" + k + "个元素");
        }
        HeroNode currNode = head.next;
        for (int i = 0; i < end; i++) {
            currNode = currNode.next;
        }
        return currNode;
    }

    /**
     * 单链表反转
     *  将原链表节点依次插入到反转链表的第一个位置
     */
    public void reverse() {
        // 如果单链表中无节点或者只有一个节点
        if (head.next == null || head.next.next == null) {
            return;
        }
        // currNode的作用：指向待插入反转链表节点的指针。遍历原单链表
        HeroNode currNode = head.next;
        // nextNode的作用：暂存待插入反转链表节点的下一个节点
        HeroNode nextNode;
        HeroNode reverseHead = new HeroNode(0, "", "");

        while (currNode != null) {
            // nextNode保存currNode.next，因为currNode.next本指向原链表，
            // currNode.next = reverseHead.next会让currNode.next指向别的地方，导致找不到原链表
            nextNode = currNode.next;
            currNode.next = reverseHead.next;
            reverseHead.next = currNode;
            // currNode每一次循环都会后移一个节点
            currNode = nextNode;
        }
        // 将原链表头节点指向反转链表头节点.next
        head.next = reverseHead.next;
    }

    /**
     * 从尾到头打印单链表
     *
     * @param
     * @return void
     */
    public void reverseList() {
        HeroNode currNode = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while (currNode != null) {
            stack.add(currNode);
            currNode = currNode.next;
        }
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            HeroNode node = stack.pop();
            System.out.printf("%d\t%s\t%s\t\n", node.no, node.name, node.nickName);
        }
    }

    /**
     * 合并两个有序链表（合并后还有序）
     *  方法一：遍历2号链表，将每一个节点都放入1号链表.addByNoOrderIsEqual方法中
     *    优点：方便简洁，代码容易理解。缺点：性能低。
     *  方法二：
     *     1号链表：1,3,6,7 2号链表：2,4,6,8
     *     由于是有序链表，2号链表的2节点插入到1号链表的1,3节点之中后，
     *     2号链表的4节点没有必要从1号节点的1,2,3开始遍历，直接从刚插入节点2节点开始遍历即可
     * @param singleLinkedList
     * @return void
     */
    public void mergeOrderLinkedList(SingleLinkedList singleLinkedList) {
        // 方法一：
        //HeroNode currNode = singleLinkedList.head.next;
        //HeroNode nextNode;
        //while (currNode != null) {
        //    nextNode = currNode.next;
        //    this.addByNoOrderIsEqual(currNode);
        //    currNode = nextNode;
        //}
        // 方法二：
        // 2号链表 currNode1当前待合并节点
        HeroNode currNode2 = singleLinkedList.head.next;
        // nextNode是currNode.next，防止2号链表后续节点丢失
        HeroNode nextNode2;
        // 缓存刚插入节点的位置
        HeroNode currNodeChche2 = null;

        while (currNode2 != null) {

            nextNode2 = currNode2.next;
            // 1号链表
            // 记录2号链表节点在1号链表节点插入的位置
            HeroNode tempNode1 = currNodeChche2;
            if (tempNode1 == null) {
                tempNode1 = this.head;
            }
            HeroNode preNode1;
            boolean flag = true; //标识该节点是否插到最后一位
            while (true) {
                if (tempNode1.next == null) {
                    break;
                }
                preNode1 = tempNode1;
                tempNode1 = tempNode1.next;
                if (currNode2.no <= tempNode1.no) {
                    // 1,3,4 nextNode.no=2 nextNode.next.no=3 2<3 可以添加
                    // nextNode指向no=3节点
                    currNode2.next = tempNode1;
                    // tempNode指向nextNode节点
                    preNode1.next = currNode2;
                    flag = false;
                    break;
                }

            }
            if (flag) {
                // 待插入节点no最大
                tempNode1.next = currNode2;
            }
            currNodeChche2 = currNode2;
            // 存储2号链表的当前节点后移
            currNode2 = nextNode2;
        }
    }
}

/**
 * 单链表的节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

}
