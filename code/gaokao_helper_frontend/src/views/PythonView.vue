<template>
  <div class="container">
    <form @submit.prevent="handleSubmit" class="form">
      <!-- <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required class="form-control" />
      </div> -->
      <div class="form-group">
        <label for="score">Score:</label>
        <input type="number" id="score" v-model="score" required class="form-control" />
      </div>
      <div class="form-group">
        <label for="rank">Rank:</label>
        <input type="number" id="rank" v-model="rank" required class="form-control" />
      </div>
      <div class="form-group">
        <label for="subject">Subject:</label>
        <select id="subject" v-model="subject" required class="form-control">
          <option value="PC">物理+化学+不限</option>
          <option value="P">物理+不限</option>
          <option value="H">历史+不限</option>
        </select>
      </div>
      <div class="form-group">
        <label for="interest">Interest:</label>
        <input type="text" id="interest" v-model="interest" required class="form-control" />
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    

    <div v-if="answer1 || answer2" class="results">
      <h3>Results:</h3>
      <table class="result-table">
        <thead>
          <tr>
            <th>Item</th>
            <th>Result</th>
          </tr>
        </thead>
        <tbody>
          <!-- <tr>
            <td>推荐专业</td>
            <td>{{ this.show }}</td>
          </tr>
          <tr>
            <td>推荐高校及专业</td>
            <td>{{ answer2 }}</td>
          </tr> -->
          <tr v-for="item in show">
            <td>推荐专业</td>
            <td>{{ item }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';


export default {
  data() {
    return {
      email: '123456@qq.com',
      score: '',
      rank: '',
      subject: '',
      interest: '',
      answer1: '',
      answer2: '',
      show: []
    };//基础数据
  },
  methods: {
    async handleSubmit() {
      try {
        const response = await axios.post('http://localhost:8000/info/api/info/', {
          email: this.email,
          score: this.score,
          rank: this.rank,
          subject: this.subject,
          interest: this.interest
        });

        this.answer1 = response.data.answer1;
        this.answer2 = response.data.answer2;
        this.show = this.answer2.split(';');
      } catch (error) {
        console.error('There was an error!', error);
      }
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.form {
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-control {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.btn {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.btn-primary {
  background-color: #007bff;
  color: white;
}

.btn-primary:hover {
  background-color: #0056b3;
}

.results {
  margin-top: 20px;
  background-color: #e9ecef;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.results h3 {
  margin-top: 0;
  font-size: 1.5em;
  color: #333;
}

.result-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

.result-table th, .result-table td {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: left;
}

.result-table th {
  background-color: #f4f4f4;
  font-weight: bold;
}

.result-table tr:nth-child(even) {
  background-color: #f9f9f9;
}

.result-table tr:hover {
  background-color: #f1f1f1;
}
</style>