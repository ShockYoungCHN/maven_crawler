package HIT;
//序号,学年学期,开课院系,课程代码,课程名称,课程性质,课程类别,学分,是否考试课,参与学分绩,补考重修标记,总成绩,最终成绩,成绩备注,学生成绩类别,成绩提交时间
public class Lesson {
    private short No;//序号
    // 学年学期,开课院系,课程代码,课程名称,课程性质(必修),课程类别(基础课、通识课),学分,是否考试课,参与学分绩,补考重修标记,最终成绩
    private String term,college,Id,name,courseNature,courseSort,isTestCourse,isAve,relearnMark;
    private float credit;
    private int finalGrade;
    public short getNo() {
        return No;
    }

    public void setNo(short no) {
        No = no;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseNature() {
        return courseNature;
    }

    public void setCourseNature(String courseNature) {
        this.courseNature = courseNature;
    }

    public String getCourseSort() {
        return courseSort;
    }

    public void setCourseSort(String courseSort) {
        this.courseSort = courseSort;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public String getIsTestCourse() {
        return isTestCourse;
    }

    public void setIsTestCourse(String isTestCourse) {
        this.isTestCourse = isTestCourse;
    }

    public String getIsAve() {
        return isAve;
    }

    public void setIsAve(String isAve) {
        this.isAve = isAve;
    }

    public String getRelearnMark() {
        return relearnMark;
    }

    public void setRelearnMark(String relearnMark) {
        this.relearnMark = relearnMark;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    public int setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
        return finalGrade;
    }
}