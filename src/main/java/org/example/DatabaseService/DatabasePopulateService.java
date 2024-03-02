package org.example.DatabaseService;

import org.example.Database;
import org.example.FileReader.SQLFileReader;
import org.example.inserts.ClientInsert;
import org.example.inserts.ProjectInsert;
import org.example.inserts.ProjectWorkerInsert;
import org.example.inserts.WorkerInsert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabasePopulateService {
    private static Connection connection = Database.getInstance().getConnection();
    private static String workerInsert = "INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)";
    private static String clientInsert = "INSERT INTO client (name) VALUES (?)";
    private static String projectInsert = "INSERT INTO project (client_id, start_date, finish_date) VALUES (?, ?, ?)";
    private static String projectWorkerInsert = "INSERT INTO project_worker (project_id, worker_id) VALUES (?, ?)";

    public static void main(String[] args) {
        populateToWorker();
        populateToClient();
        populateToProject();
        populateToProjectWorker();
    }

    private static void populateToWorker() {
        try (PreparedStatement queryStatement = connection.prepareStatement(workerInsert)) {
            for (WorkerInsert worker : getWorkers()) {
                queryStatement.setString(1, worker.getName());
                queryStatement.setString(2, worker.getBirthday());
                queryStatement.setString(3, worker.getLevel());
                queryStatement.setInt(4, worker.getSalary());
                queryStatement.addBatch();
            }
            queryStatement.executeBatch();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }

    private static void populateToClient() {
        try (PreparedStatement queryStatement = connection.prepareStatement(clientInsert)) {
            for (ClientInsert client : getClients()) {
                queryStatement.setString(1, client.getName());
                queryStatement.addBatch();
            }
            queryStatement.executeBatch();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }

    private static void populateToProject() {
        try (PreparedStatement queryStatement = connection.prepareStatement(projectInsert)) {
            for (ProjectInsert project : getProjects()) {
                queryStatement.setInt(1, project.getClientId());
                queryStatement.setString(2, project.getStartDate());
                queryStatement.setString(3, project.getFinishDate());
                queryStatement.addBatch();
            }
            queryStatement.executeBatch();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }

    private static void populateToProjectWorker() {
        try (PreparedStatement queryStatement = connection.prepareStatement(projectWorkerInsert)) {
            for (ProjectWorkerInsert projectWorker : getProjectWorkers()) {
                queryStatement.setInt(1, projectWorker.getWorkerId());
                queryStatement.setInt(2, projectWorker.getProjectId());
                queryStatement.addBatch();
            }
            queryStatement.executeBatch();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }

    private static List<ProjectWorkerInsert> getProjectWorkers() {
        List<ProjectWorkerInsert> projectWorkers = new ArrayList<>();
        projectWorkers.add(new ProjectWorkerInsert(1, 1));
        projectWorkers.add(new ProjectWorkerInsert(2, 2));
        projectWorkers.add(new ProjectWorkerInsert(2, 3));
        projectWorkers.add(new ProjectWorkerInsert(3, 4));
        projectWorkers.add(new ProjectWorkerInsert(4, 5));
        projectWorkers.add(new ProjectWorkerInsert(5, 6));
        projectWorkers.add(new ProjectWorkerInsert(5, 7));
        projectWorkers.add(new ProjectWorkerInsert(6, 1));
        projectWorkers.add(new ProjectWorkerInsert(7, 2));
        projectWorkers.add(new ProjectWorkerInsert(8, 9));
        projectWorkers.add(new ProjectWorkerInsert(9, 8));
        projectWorkers.add(new ProjectWorkerInsert(9, 9));
        projectWorkers.add(new ProjectWorkerInsert(9, 10));
        projectWorkers.add(new ProjectWorkerInsert(10, 2));
        return projectWorkers;
    }

    private static List<ProjectInsert> getProjects() {
        List<ProjectInsert> projects = new ArrayList<>();
        projects.add(new ProjectInsert(1, "2023-01-01", "2023-12-15"));
        projects.add(new ProjectInsert(2, "2023-02-10", "2023-04-25"));
        projects.add(new ProjectInsert(3, "2023-03-20", "2023-09-05"));
        projects.add(new ProjectInsert(3, "2023-04-05", "2023-06-20"));
        projects.add(new ProjectInsert(5, "2023-05-15", "2023-09-30"));
        projects.add(new ProjectInsert(1, "2023-06-01", "2023-10-15"));
        projects.add(new ProjectInsert(2, "2023-07-10", "2023-09-25"));
        projects.add(new ProjectInsert(3,  "2023-08-20", "2023-10-05"));
        projects.add(new ProjectInsert(4, "2023-09-05", "2023-11-20"));
        projects.add(new ProjectInsert(5, "2023-10-15", "2023-12-30"));
        return projects;
    }

    private static List<ClientInsert> getClients() {
        List<ClientInsert> clients = new ArrayList<>();
        clients.add(new ClientInsert("Sam"));
        clients.add(new ClientInsert("Antony"));
        clients.add(new ClientInsert("Anne"));
        clients.add(new ClientInsert("Jade"));
        clients.add(new ClientInsert("Luke"));
        return clients;
    }

    private static List<WorkerInsert> getWorkers() {
        List<WorkerInsert> workers = new ArrayList<>();
        workers.add(new WorkerInsert("John", "1990-05-15", "Trainee", 800));
        workers.add(new WorkerInsert( "Alice", "1985-09-22", "Junior", 1200));
        workers.add(new WorkerInsert("Bob", "1982-03-10", "Middle", 3000));
        workers.add(new WorkerInsert("Emily", "1995-12-03", "Senior", 5500));
        workers.add( new WorkerInsert(  "Michael", "1988-07-18", "Trainee", 900));
        workers.add(new WorkerInsert( "Sophia", "1992-11-27", "Junior", 1100));
        workers.add(new WorkerInsert(  "William", "1984-04-05", "Middle", 3200));
        workers.add(new WorkerInsert(  "Olivia", "1997-08-14", "Senior", 5200));
        workers.add(new WorkerInsert( "James", "1993-02-28", "Trainee", 950));
        workers.add(new WorkerInsert( "Emma", "1989-06-20", "Junior", 1300));
        return workers;
    }
}
