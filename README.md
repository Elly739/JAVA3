# Student Form Swing + SQLite (CAT)

This project provides a Java Swing student form (left) and a table (right) storing student records in a local SQLite file `students.db`. The application entry class is CAT (app.CAT).

Files (package app):
- CAT.java
- StudentFormPanel.java
- Database.java
- Student.java

Requirements:
- JDK 8+
- sqlite-jdbc driver jar (e.g., sqlite-jdbc-3.42.0.0.jar)

Compile & Run (Linux/macOS):
1. Put the files under folder `app/` (matching package).
2. Place `sqlite-jdbc-3.42.0.0.jar` in the parent folder.
3. Compile:
   ```bash
   javac -cp ".:sqlite-jdbc-3.42.0.0.jar" app/*.java
   ```
4. Run:
   ```bash
   java -cp ".:sqlite-jdbc-3.42.0.0.jar:." app.CAT
   ```

Windows (use `;` as classpath separator):
```powershell
javac -cp ".;sqlite-jdbc-3.42.0.0.jar" app\*.java
java -cp ".;sqlite-jdbc-3.42.0.0.jar;." app.CAT
```

What to screenshot for the assignment:
- The running GUI showing the form (left) and table (right) with at least one saved record.
- Database contents (open `students.db` with DB Browser for SQLite or use `sqlite3` CLI):
  ```
  sqlite3 students.db
  sqlite> .tables
  sqlite> SELECT * FROM students;
  ```

Notes:
- If you want the class packaged differently or prefer a different class name, I can update imports/README accordingly.
- If you'd like this converted to a Maven project (pom.xml with sqlite JDBC dependency) or zipped up for upload to GitHub, tell me and I will prepare it.
