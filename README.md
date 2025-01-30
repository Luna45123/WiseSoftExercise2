# 📅 ระบบจัดการหัวข้อสัมมนา

โปรแกรม **Seminar Scheduler** เป็นระบบที่ช่วยจัดตารางสัมมนาโดยอัตโนมัติ โดยแบ่งหัวข้อออกเป็นช่วงเช้าและช่วงบ่าย ตามข้อจำกัดของเวลา โปรแกรมจะอ่านข้อมูลจาก ไฟล์ข้อความ (text file) โดยการใส่ Path ของไฟล์และสร้างตารางสัมมนาที่เหมาะสม

---

## 🚀 **คุณสมบัติของโปรแกรม**
- จัดหัวข้อสัมมนาให้เหมาะสมกับ **ช่วงเช้าและช่วงบ่าย** โดยอัตโนมัติ
- หัวข้อที่ไม่สามารถจัดลงวันเดียวกันได้ **จะถูกย้ายไปวันถัดไป**
- ใช้ **ปีพุทธศักราช (พ.ศ.)** ในการแสดงวันที่

---
# 📥 **รูปแบบไฟล์อินพุต (Input)**
ไฟล์ที่ใช้เป็น .txt โดยต้องมีโครงสร้างดังนี้:
```
YYYY-MM-DD
ชื่อหัวข้อสัมมนา 60min
ช่อหัวข้อสัมมนา 45min
ชื่อหัวข้อสัมมนา 30min
```
---
# วิธีใช้งาน
- รันโปรแกรม ระบบจะให้ป้อนที่อยู่ไฟล์
- ใส่ที่อยู่ไฟล์อินพุต (ตัวอย่าง: src/input.txt)
- ระบบจะจัดตารางสัมมนาและแสดงผลลัพธ์บนหน้าจอ

ตัวอย่าง input
````
2022-02-25
Writing Fast Tests Against Enterprise Rails 60min
Overdoing it in Python 45min
Lua for the Masses 30min
Ruby Errors from Mismatched Gem Versions 45min
Ruby on Rails Legacy App Maintenance 60min
Common Ruby Errors 45min
User Interface CSS in Rails Apps 30min
Pair Programming vs Noise 45min
Ruby vs. Clojure for Back-End Development 30min
Communicating Over Distance 60min
Rails Magic 60min
Woah 30min
Sit Down and Write 30min
Accounting-Driven Development 45min
Ruby on Rails: Why We Should Move On 60min
Clojure Ate Scala (on my project) 45min
Programming in the Boondocks of Seattle 30min
A World Without HackerNews 30min
Rails for Python Developers 5min
````
ตัวอย่าง output
````
Day 1 - 25/02/2565
09:00am Writing Fast Tests Against Enterprise Rails 60min
10:00am Overdoing it in Python 45min
10:45am Lua for the Masses 30min
11:15am Ruby Errors from Mismatched Gem Versions 45min
12:00pm Lunch
01:00pm Ruby on Rails Legacy App Maintenance 60min
02:00pm Common Ruby Errors 45min
02:45pm User Interface CSS in Rails Apps 30min
03:15pm Pair Programming vs Noise 45min
04:00pm Programming in the Boondocks of Seattle 30min
04:30pm A World Without HackerNews 30min
05:00pm Networking Event

Day 2 - 28/02/2565
09:00am Ruby vs. Clojure for Back-End Development 30min
09:30am Communicating Over Distance 60min
10:30am Rails Magic 60min
11:30am Woah 30min
12:00pm Lunch
01:00pm Sit Down and Write 30min
01:30pm Accounting-Driven Development 45min
02:15pm Ruby on Rails: Why We Should Move On 60min
03:15pm Clojure Ate Scala (on my project) 45min
04:00pm Rails for Python Developers 5min
04:05pm Networking Event
````

