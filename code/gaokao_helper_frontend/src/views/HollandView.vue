<template>
  <div class="container">
    <h1>霍兰德职业兴趣测试</h1>
    <form @submit.prevent="calculateResult" ref="hollandTest">
      <div class="question" v-for="(question, index) in questions" :key="index">
        <div class="question-header">{{ question.text }}</div>
        <div class="question-options">
          <label>
            <input type="radio" :name="`q${index + 1}`" :value="question.value" required /> 是
          </label>
          <label>
            <input type="radio" :name="`q${index + 1}`" value="0" /> 否
          </label>
        </div>
      </div>
      <div class="button-container">
        <button type="submit">提交测试</button>
      </div>
    </form>
    <div class="result" v-if="showResult">
      <p>霍兰德职业兴趣类型</p>
      <p><strong>{{ topThreeTypes }}</strong></p>
      <p>对应职业</p>
      <p><strong>{{ combinationJobsText }}</strong></p>
      <p>各类型得分</p>
      <p><strong v-html="scoreText"></strong></p>
      <p>少数情况下得分最高的三个类型存在并列，可结合霍兰德人格六角形等图表进一步判断</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      questions: [
        { text: "1. 强壮而敏捷的身体对我很重要。", value: "R" },
        { text: "2. 我必须彻底地了解事情的真相。", value: "I" },
        { text: "3. 我的心情受音乐、色彩和美丽事物的影响极大。", value: "A" },
        { text: "4. 和他人的的关系丰富了我的生命并使它有意义。", value: "S" },
        { text: "5. 我自信会成功。", value: "E" },
        { text: "6. 我做事必须有清楚的指引。", value: "C" },
        { text: "7. 我擅长于自己制作、修理东西。", value: "R" },
        { text: "8. 我可以花很长的时间去想通事情的道理。", value: "I" },
        { text: "9. 我重视美丽的环境。", value: "A" },
        { text: "10. 我愿意花时间帮别人解决个人危机。", value: "S" },
        { text: "11. 我喜欢竞争。", value: "E" },
        { text: "12. 我在开始一个计划前会花很多时间去计划。", value: "C" },
        { text: "13. 我喜欢使用双手做事。", value: "R" },
        { text: "14. 探索新构思使我满意。", value: "I" },
        { text: "15. 我是寻求新方法来发挥我的创造力。", value: "A" },
        { text: "16. 我认为能把自己的焦虑和别人分担是很重要的。", value: "S" },
        { text: "17. 成为群体中的关键任务执行者，对我很重要。", value: "E" },
        { text: "18. 我对于自己能重视工作中的所有细节感到骄傲。", value: "C" },
        { text: "19. 我不在乎工作把手弄脏。", value: "R" },
        { text: "20. 我认为教育是个发展及磨练脑力的终身学习过程。", value: "I" },
        { text: "21. 我喜欢非正式的穿着，尝试新颜色和款式。", value: "A" },
        { text: "22. 我常能体会到某人想要和他人沟通的需要。", value: "S" },
        { text: "23. 我喜欢帮助别人不断改进。", value: "S" },
        { text: "24. 我在决策时，通常不愿冒险。", value: "C" },
        { text: "25. 我喜欢购买小零件，做成成品。", value: "R" },
        { text: "26. 有时我长时间阅读，玩拼图游戏，冥想生命本质。", value: "I" },
        { text: "27. 我有很强的想象力。", value: "A" },
        { text: "28. 我喜欢帮助别人发挥天赋和才能。", value: "S" },
        { text: "29. 我喜欢监督事情直至完工。", value: "E" },
        { text: "30. 如果我面对一个新情景，会在事前做充分的准备。", value: "C" },
        { text: "31. 我喜欢独立完成一项任务。", value: "R" },
        { text: "32. 我渴望阅读或思考任何可以引发我好奇心的东西。", value: "I" },
        { text: "33. 我喜欢尝试创新的概念。", value: "A" },
        { text: "34. 如果我和别人摩擦，我会不断尝试化干戈为玉帛。", value: "S" },
        { text: "35. 要成功就必须定高目标。", value: "E" },
        { text: "36. 我喜欢为重大决策负责。", value: "C" },
        { text: "37. 我喜欢直言不讳，不喜欢转弯抹角。", value: "R" },
        { text: "38. 我在解决问题前，必须把问题进行彻底分析。", value: "I" },
        { text: "39. 我喜欢重新布置我的环境，使他们与众不同。", value: "A" },
        { text: "40. 我经常借着和别人交谈来解决自己的问题。", value: "S" },
        { text: "41. 我常想起草一个计划，而由别人完成细节。", value: "R" },
        { text: "42. 准时对我来说非常重要。", value: "C" },
        { text: "43. 从事户外活动令我神清气爽。", value: "S" },
        { text: "44. 我不断地问：为什么？", value: "I" },
        { text: "45. 我喜欢自己的工作能够抒发我的情绪和感觉。", value: "A" },
        { text: "46. 我喜欢帮助别人找可以和他人相互关注的办法。", value: "S" },
        { text: "47. 能够参与重大决策是件令人兴奋的事情。", value: "E" },
        { text: "48. 我经常保持清洁，喜欢有条不紊。", value: "C" },
        { text: "49. 我喜欢周边环境简单而实际。", value: "R" },
        { text: "50. 我会不断地思索一个问题，直到找出答案为止。", value: "I" },
        { text: "51. 大自然的美深深地触动我的灵魂。", value: "A" },
        { text: "52. 亲密的人际关系对我很重要。", value: "S" },
        { text: "53. 升迁和进步对我极重要。", value: "E" },
        { text: "54. 当我把每日工作计划好时，我会较有安全感。", value: "C" },
        { text: "55. 我不害怕过重工作负荷，且知道工作的重点。", value: "R" },
        { text: "56. 我喜欢能使我思考、给我新观念的书。", value: "I" },
        { text: "57. 我希望能看到艺术表演、戏剧及好的电影。", value: "A" },
        { text: "58. 我对别人的情绪低潮相当的敏感。", value: "S" },
        { text: "59. 能影响别人使我感到兴奋。", value: "S" },
        { text: "60. 当我答应一件事时，我会竭尽监督所有细节。", value: "C" },
        // { text: "61. 我希望粗重的肢体工作不会伤害任何人。", value: "R" },
        // { text: "62. 我希望能学习所有使我感兴趣的科目。", value: "I" },
        // { text: "63. 我希望能做些与众不同的事。", value: "A" },
        // { text: "64. 我对别人的困难乐于伸出援手。", value: "S" },
        // { text: "65. 我愿意冒一点险以求进步。", value: "E" },
        // { text: "66. 当我遵循成规时，我感到安全。", value: "C" },
        // { text: "67. 我选车时，最先注意的是好的引擎。", value: "R" },
        // { text: "68. 我喜欢能刺激我思考的话。", value: "I" },
        // { text: "69. 当我从事创造性的事时，我会忘掉一切旧经验。", value: "A" },
        // { text: "70. 我对社会上有许多人需要帮助感到关注。", value: "S" },
        // { text: "71. 说服别人依计划行事是件有趣的事情。", value: "E" },
        // { text: "72. 我擅长于检查细节。", value: "C" },
        // { text: "73. 我通常知道如何应付紧急事件。", value: "R" },
        // { text: "74. 阅读新发现的书事件令人兴奋的事情。", value: "I" },
        // { text: "75. 我喜欢美丽、不平凡的东西。", value: "A" },
        // { text: "76. 我经常关心孤独、不友善的人。", value: "S" },
        // { text: "77. 我喜欢讨价还价。", value: "E" },
        // { text: "78. 我花钱时小心翼翼。", value: "C" },
        // { text: "79. 我用运动来保持强壮的身体。", value: "R" },
        // { text: "80. 我经常对大自然的奥秘感到好奇。", value: "I" },
        // { text: "81. 尝试不平凡的新事物是件相当有趣的事情。", value: "A" },
        // { text: "82. 当别人像我诉说他的困难时，我是个好听众。", value: "S" },
        // { text: "83. 做事失败了，我会再接再厉。", value: "E" },
        // { text: "84. 我需要确切地知道别人对我的要求是什么。", value: "R" },
        // { text: "85. 我喜欢把东西拆开，看看能否修理他们。", value: "I" },
        // { text: "86. 我喜欢研读所有的事实，再有逻辑的做出决定。", value: "I" },
        // { text: "87. 没有美丽事物的生活，对我而言是不可思议的。", value: "A" },
        // { text: "88. 人们经常告诉我他们的问题。", value: "S" },
        // { text: "89. 我常能借着资讯网络和别人取得联系。", value: "S" },
        // { text: "90. 小心谨慎的完成一件事是件有成就感的事情。", value: "C" }
      ],
      scores: { R: 0, I: 0, A: 0, S: 0, E: 0, C: 0 },
      showResult: false,
      topThreeTypes: "",
      combinationJobsText: "",
      scoreText: "",
    };
  },
  methods: {
    calculateResult() {
      Object.keys(this.scores).forEach((key) => (this.scores[key] = 0));
      const form = this.$refs.hollandTest;
      const formData = new FormData(form);

      for (let [, value] of formData.entries()) {
        if (value !== "0") {
          this.scores[value]++;
        }
      }

      let sortedScores = Object.entries(this.scores).sort(
        (a, b) => b[1] - a[1]
      );
      this.topThreeTypes = sortedScores
        .slice(0, 3)
        .map((entry) => entry[0])
        .join("");
      this.scoreText = sortedScores
        .map(([type, score]) => `${type}: ${score}`)
        .join("<br>");

        const jobCodeMapping = {
        // 这里放置 jobCodeMapping 对象
        RIA: ["牙科技术员、陶工、建筑设计员、模型工、细木工、制作链条人员"],
        RIS: ["厨师、林务员、跳水员、潜水员、染色工、电器修理、眼镜制作、电工、纺织机械装配工、报务员、装玻璃工人、发电厂操作工人、焊接工"],
        RIE: ["建筑和桥梁工程、环境工程、航空工程、公路工程、电力工程、信号工程、电话工程、一般机械工程、自动工程、矿业工程、海洋工程、交通工程技术人员、制图员、家政经济人员、打捞员、计量员、农民、农场工人、农业机器操作、清洁工、无线电修理、汽车修理、手表修理、管子工、线路维修、盖（修）房工、电子技术员、代木工、机械师"],
        RIC: ["船上工作人员。接待员、杂志保管员、牙科医生的助手、制帽工、磨坊工、石匠、机器制造、机车（火车头）制造、农业机器装配、汽车装配工、缝纫机装配工、钟表装配和检验、电动器具装配、鞋匠、锁匠、货物检验员、电梯机修工、托儿所所长、钢琴调音工、装配工、印刷工、建筑钢铁工人、卡车司机"],
        RAI: ["手工雕刻、玻璃雕刻、制作模型人员、家具木工、制作皮革品、手工绣花、手工钩针纺织、排字工人、印刷拼板工人、图画雕刻、装订工"],
        RSE: ["消防员、交通巡警、门卫、理发师、房间清洁工、屠夫、锻工、开凿人、管道安装工、出租汽车驾驶员、仓库管理员"],
        RSC: ["汽车驾驶员、货物搬运工、送报员、勘探员、娱乐场所的服务员、起卸机操作工、灭害虫者、电梯操作工、厨房助手"],
        RSI: ["纺织工、纺织工、农业学校的教师、某些职业课程教师（诸如艺术、商业、技术、工艺课程）、雨衣上胶工人"],
        REC: ["抄水表员、保姆、实验室动物饲养员、动物管理员"],
        REI: ["轮船船长、航海领航员、大副、试管实验员"],
        RES: ["旅馆服务员、家畜饲养员、渔民、渔网修补工、水手长、收割机操作工、搬行李工人、公园服务员、救生员、登山导游、火车工程技术员、建筑工人、铺轨工人"],
        RCI: ["测量员、勘测员、仪器操作者、农业工程技师、化学工程师、民用工程技师、石工程师、资料室管理员、探矿工、煅烧工、烧窑工、矿工、保养工、磨床工、取样员、样品检验员、纺纱工、炮手、绕筒子工、漂洗工、电焊工、锯木工、刨床工、制帽工、手工缝纫、油漆工、染色工、按摩师、木匠、农民、建筑工人、电影放映员、勘测员助手"],
        RCS: ["公共汽车驾驶员、一等水手、游泳池服务员、裁缝、建筑工人、石匠、烟囱修理工、水磨石工、泥水匠、车工、烟囱修建工、混凝土工、电话修理工、爆炸手、邮递员、矿工、裱糊工人、纺纱工"],
        RCE: ["打井工、吊车驾驶员、农场工人、邮件分类员、铲车司机、拖拉机司机"],
        IAS: ["普通经济学家、农业经济学家、财政经济学家、国际贸易经济学家、实验心理学家、工程心理学家、心理学家、哲学家、内科医生、数学家"],
        IAR: ["人类学家、天文学家、化学家、物理学家、医学病理学家、动物标本录制者、化石修复者、艺术品管理员"],
        ISE: ["营养学家、饮食顾问、火灾检查员、邮政服务检查员"],
        ISC: ["侦察员、电视播音室修理工、电视修理服务员、验尸室人员、编目录的人、医学实验室技师、调查研究者"],
        ISR: ["水生生物学者、昆虫学家、微生物学家、配镜师、矫正视力者、细菌学家、牙科医生、骨科医生"],
        ISA: ["实验心理学家、普通心理学家、发展心理学家、教育心理学家、社会心理学家、临床心理学家、目录学家、皮肤病学家、神经病学家、妇产科医生、眼科医生、五官科医生、医学实验室技术专家、民航医务人员、护士"],
        IES: ["细菌学家、生理学家、化学专家、地质专家、地理物理学专家、纺织技术专家、医院药剂师、工业药剂师、药房营业员"],
        IEC: ["档案保管员、保险统计员"],
        ERI: ["建筑物管理员、工业工程师、农场管理员、护士长、农业经营管理人员"],
        ERS: ["仓库管理员、房屋管理员、货栈监督人"],
        ERC: ["邮政局长、渔船船长、机械操作领班、木工领班、瓦工领班、驾驶员领班"],
        EIR: ["科学、技术和有关周期出版物的管理员"],
        EIC: ["专利代理人、鉴定人、运输服务检查员、安全检查员、废品收购人员"],
        EIS: ["警官、侦察员、交通检查员、安全咨询者、合同管理者、商人"],
        EAS: ["法官、律师、公证人"],
        EAR: ["展览室管理员、舞台管理员、播音员、训兽员"],
        ESC: ["理发师、裁判员、政府行政管理员、财政管理员、工程管理员、职业病防治、售货员、商业经理、办公室主任、人事负责人、高度员"],
        ESR: ["家具售货员、书店售货员、公共汽车的驾驶员、日用商品的售货员、护士长、自然科学和工程的行政领导"],
        ESI: ["博物馆管理员、图书馆管理员、古迹管理员、饮食业经理、地区安全服务管理员、技术服务咨询者、超级市场管理员、零售商品店店员、批发商、出租汽车服务站高度"],
        ESA: ["博物馆馆长、报刊管理员、音乐器材售货员、广告商、售画营业员、导游、（轮船或班机上的）事务长、飞机上的服务员、船员、法官、律师"],
        ASE: ["戏剧导演、舞蹈教师、广告撰稿人、报刊专栏作者、记者、演员、英语导游、外语翻译"],
        ASI: ["音乐教师、乐器教师、美术教师、管弦乐指挥、合唱队指挥、歌星、演奏家、哲学家、作家、广告经理、时装模特儿"],
        AER: ["新闻摄影师、电视摄像师、艺术指导、录音指导、丑角演员、魔术师、木偶戏演员、骑士、跳水员"],
        AEI: ["音乐指挥、舞台指导、电影导演"],
        AES: ["流行歌手、舞蹈演员、电影导演、广播节目主持人、舞蹈教师、口技表演者、喜剧演员、模特儿"],
        AIS: ["画家、剧作家、编辑、评论家、时装艺术大师、家具设计师、包装设计师、布景设计师、服装设计师、新闻摄影师、男演员、文学作者"],
        AIE: ["花匠、皮衣设计师、工业产品设计师、剪影艺术家、复制雕刻品大师"],
        AIR: ["建筑师、画家、摄影师、绘图员、环境美化工、雕刻家、包装设计师、陶器设计师、绣花工、漫画家"],
        SEC: ["社会活动家、退伍军人服务官员、工商会事务代表、教育咨询者、宿舍管理员、旅馆经理、饮食服务管理员"],
        SER: ["体育教练、游泳指导"],
        SEI: ["大学校长、学院院长、医院行政管理员、历史学家、家政经济学家、职业学校教师、资料员"],
        SEA: ["娱乐活动管理员、国外服务办事员、社会服务助理、一般咨询者宗教教育工作者"],
        SCE: ["部长助理、福利机构职员、生产协调人、环境卫生管理人员、戏院经理、餐馆经理、售票员"],
        SRI: ["外科医师助手、医院服务员"],
        SRE: ["体育教师、职业病治疗者、体育教练、专业运动员、房管员、儿童家庭教师、警察、引座员、传达员、保姆"],
        SRC: ["护理员、护理助手、医院勤杂工、理发师、学校儿童服务人员"],
        SIA: ["社会学家、心理咨询者、学校心理学家、政治科学家、大学或学院的系主任、大学或学院的教育学教师、大学农业教师、大学工程和建筑课程的教师、大学数学、医学、物理、社会科学和生命科学的教师、研究生助教、成人教育教师"],
        SAC: ["理发师、指甲修剪师、包装艺术家、美容师、整容专家、发式设计师"],
        SAE: ["听觉病治疗者、演讲矫正者"],
        SAI: ["图书馆管理员、小学教师、幼儿园教师、学前儿童教师、中学教师、师范学院的教师、盲人教师、智力障碍人的教师、聋哑人的教师、学校护士、牙科助理、飞行指挥员"],
        SIE: ["营养学家、饮食学家、海关检查员、安全检查员、税务稽查员、校长"],
        SIC: ["描图员、兽医助手、诊所助理、体检检查员、监督缓邢犯的工作者、娱乐指导员、咨询人员、社会科学教师"],
        SIR: ["理疗员、救护队工作人员、手足病医生、职业病治疗助手"]
      };

      this.combinationJobsText = jobCodeMapping[this.topThreeTypes]
        ? `${this.topThreeTypes}: ${jobCodeMapping[this.topThreeTypes].join(", ")}`
        : `${this.topThreeTypes}: 没有找到对应的职业`;

      this.showResult = true;
    },
  },
};
</script>

<style scoped>
body {
  font-family: "HarmonyOS Sans SC", Arial, sans-serif;
  background-color: #f4f4f4;
  margin: 0;
  padding: 20px;
  color: #333;
}

.container {
  max-width: 700px;
  margin: 0 auto;
  background: #fff;
  padding: 30px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.05);
  border-radius: 8px;
}

h1 {
  text-align: center;
  color: #000;
  margin-bottom: 30px;
  font-size: 28px;
}

.question {
  margin-bottom: 20px;
}

.question-header {
  font-weight: bold;
  color: #222;
  margin-bottom: 10px;
  font-size: 18px;
}

.question-options label {
  display: block;
  margin: 5px 0;
  font-size: 16px;
}

.button-container {
  text-align: center;
  margin-top: 30px;
}

button {
  padding: 12px 24px;
  background: #333;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s, transform 0.3s;
}

button:hover {
  background: #222;
  transform: scale(1.05);
}

.result {
  margin-top: 30px;
  padding: 20px;
  background: #ecf0f1;
  border: 1px solid #dcdcdc;
  border-radius: 8px;
}

@media (max-width: 768px) {
  .container {
    padding: 20px;
  }

  h1 {
    font-size: 24px;
  }

  .question-header {
    font-size: 16px;
  }

  .question-options label {
    font-size: 14px;
  }

  button {
    padding: 10px 20px;
    font-size: 14px;
  }
}
</style>
