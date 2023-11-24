// Import necessary modules
const chai = require('chai');
const expect = chai.expect;
const supertest = require('supertest');
const app = require('../index'); // Import your Express app instance

// Define the API endpoint
const apiEndpoint = '/api';

// Create a test suite
describe('Todos API', () => {
    let createdTodoId;

    // Test the GET /todos endpoint
    describe('GET /api/post/', () => {
        it('should get all post', async () => {
            const response = await supertest(app).get(apiEndpoint);
            expect(response.status).to.equal(200);
            expect(response.body).to.be.an('array');
        });
    });
    describe('GET /api/booking/getbookings', () => {
        it('should get all booking', async () => {
            const response = await supertest(app).get(apiEndpoint);
            expect(response.status).to.equal(200);
            expect(response.body).to.be.an('array');
        });
    });
    describe('GET /api/booking/getfeedbacks', () => {
        it('should get all feedback', async () => {
            const response = await supertest(app).get(apiEndpoint);
            expect(response.status).to.equal(200);
            expect(response.body).to.be.an('array');
        });
    });
    describe('GET /api/chef/profile/', () => {
        it('should get all profile', async () => {
            const response = await supertest(app).get(apiEndpoint);
            expect(response.status).to.equal(200);
            expect(response.body).to.be.an('array');
        });
    });
    describe('GET /api/post/', () => {
        it('should get all post', async () => {
            const response = await supertest(app).get(apiEndpoint);
            expect(response.status).to.equal(200);
            expect(response.body).to.be.an('array');
        });
    });
    // Test the POST /todos endpoint
    describe('POST /todos', () => {
        it('should create a new todo', async () => {
            const newTodo = { title: 'Test Todo', completed: false };
            const response = await supertest(app)
                .post(apiEndpoint)
                .send(newTodo);

            expect(response.status).to.equal(201);
            expect(response.body).to.have.property('id');
            createdTodoId = response.body.id;
        });
    });

    // Test the GET /todos/:id endpoint
    describe('GET /api/booking/getfeedback/:id', () => {
        it('should get a specific feedback', async () => {
            const response = await supertest(app).get(`${apiEndpoint}/${createdTodoId}`);
            expect(response.status).to.equal(200);
            expect(response.body).to.have.property('id', createdTodoId);
        });
    });
    describe('GET /api/booking/getbookingbyid/:id', () => {
        it('should get a specific booking', async () => {
            const response = await supertest(app).get(`${apiEndpoint}/${createdTodoId}`);
            expect(response.status).to.equal(200);
            expect(response.body).to.have.property('id', createdTodoId);
        });
    });
    describe('GET /api/chef/profile/:id', () => {
        it('should get a specific profile', async () => {
            const response = await supertest(app).get(`${apiEndpoint}/${createdTodoId}`);
            expect(response.status).to.equal(200);
            expect(response.body).to.have.property('id', createdTodoId);
        });
    });
    describe('GET /api/chef/getschedule/:id', () => {
        it('should get a specific schedule', async () => {
            const response = await supertest(app).get(`${apiEndpoint}/${createdTodoId}`);
            expect(response.status).to.equal(200);
            expect(response.body).to.have.property('id', createdTodoId);
        });
    });
    describe('GET /api/chef/getschedule/:id', () => {
        it('should get a specific schedule', async () => {
            const response = await supertest(app).get(`${apiEndpoint}/${createdTodoId}`);
            expect(response.status).to.equal(200);
            expect(response.body).to.have.property('id', createdTodoId);
        });
    });

    // Test the PUT /todos/:id endpoint
    describe('PUT /todos/:id', () => {
        it('should update a todo', async () => {
            const updatedTodo = { title: 'Updated Todo', completed: true };
            const response = await supertest(app)
                .put(`${apiEndpoint}/${createdTodoId}`)
                .send(updatedTodo);

            expect(response.status).to.equal(200);
            expect(response.body).to.have.property('title', updatedTodo.title);
            expect(response.body).to.have.property('completed', updatedTodo.completed);
        });
    });

    // Test the DELETE /todos/:id endpoint
    describe('DELETE /todos/:id', () => {
        it('should delete a todo', async () => {
            const response = await supertest(app).delete(`${apiEndpoint}/${createdTodoId}`);
            expect(response.status).to.equal(204);
        });
    });
});
