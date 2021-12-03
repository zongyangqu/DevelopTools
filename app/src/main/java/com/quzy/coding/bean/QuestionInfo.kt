package com.quzy.coding.bean

import java.util.*

/**
 * CreateDate:2021/12/3 14:59
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class QuestionInfo {

    /**
     * 题目ID
     */
    var question_id = ""

    /**
     * 题目
     */
    var title = ""


    /**
     * 题目答案（题目选项数组KEY+1）
     */
    var answer = ""

    /**
     * 解释
     */
    var explain = ""


    /**
     * 今日是否答题：1-已答题、2-未答题,-1是网络异常type
     */
    var type = "1"

    /**
     * 今日是否答题：1-已答题、2-未答题,-1是网络异常type,3-完成所有题目
     */
    var card_type = 0

    /**
     * 答题结果：0-错误、1-正确,3-完成所有题目
     */
    var is_correct = ""

    /**
     * 用户作答正确总数
     */
    var correct_count = ""

    /**
     * 用户答题总数（当答题总数在6题及6题以上时展示排名）
     */
    var total_count = ""

    /**
     * 用户排名（当答题总数在6题及6题以上时展示排名）默认为0
     */
    var rank = ""

    /**
     * 题目选项,["选项A","选项B"]
     */
    var options: List<String> = ArrayList()

    /**
     * 用户选择的选项,从1开始，选项A就是1
     */
    var option = ""

    var img_url = ""
}