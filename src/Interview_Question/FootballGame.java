package Interview_Question;import org.omg.PortableInterceptor.INACTIVE;import java.util.*;/** *科大讯飞java岗笔试 * * 球赛 * * 题目描述： *你的 大学生足协决定举办全国性的大学生足球赛，由每个学校派遣一支队伍代表该校参赛。 * 你比赛分区分为几个赛区进行，最终的总决赛中，将有不超过n支队伍参加。经过激烈的角逐，有机会参与总决赛的队伍已经决出。 * * 协会对比赛的规则进行了调整，以便使得比赛更具有观赏性。 * 1.?总决赛的参赛队伍为n支，n为偶数； * 2.?进入前1/2的队伍才有资格进入淘汰赛； * 3.?队伍按积分排名，具体规则为：胜一场积3分；平一场积1分；负一场积0分。队伍首先按积分降序排列，积分相同按净胜球数降序排列，仍然相同的按进球数降序排列。 * 4.?基于上述规则，尚未出现有排名歧义的情况发生。 * ? * * 随着赛程的进行，目前各个队伍对战的结果已经确定了，小B负责确定进入淘汰赛的名单，她向你求助，你能帮她吗？ * * 输入 * 测试数据有多组，每组测试数据的第一行为一个整数n（1=< n <=50），为参与总决赛的球队数， * 随后的n行为球队的名字，由不超过30个的大小写拉丁字母构成。 * 随后的n*(n-1)/2行为赛事的开展情况， * 每行的格式为name1-name2 num1:num2，表示两支队伍的比分情况（1=<num1, num2<=100）。 * * 确保不会有两支队伍同名，也不会出现队伍自己通自己比赛的情况，且每场比赛仅出现一次。 * * 输出 * 对每组测试数据，输出n/2行，为按字母序排列的进入淘汰赛的n/2支队伍的名单，每个名字在单独的行中输出。 * * 样例输入 * 4 * A * B * C * D * A-B 1:1 * A-C 2:2 * A-D 1:0 * B-C 1:0 * B-D 0:3 * C-D 0:3 * * 样例输出 * A * D * * 样例输入 2 a A a-A 2:1 * * 样例输出 * a */public class FootballGame {    public static void main(String[] args) {        List<Team> teams = new ArrayList<>();        HashMap<String,Team> teamMap = new HashMap<>();        Scanner sc = new Scanner(System.in);        int n = sc.nextInt();        sc.nextLine();        for (int i = 0; i<n; i++){            String name =sc.nextLine();            Team team = new Team(name);            teams.add(team);            teamMap.put(name,team);        }        int num = n*(n-1)/2;        for (int i=0; i<num; i++){            String str = sc.nextLine();            int index = str.indexOf("-");            int index2 = str.indexOf(" ");            int index3 = str.indexOf(":");            String team1 = str.substring(0,index);            String team2 = str.substring(index+1,index2);            int score1 = Integer.parseInt(str.substring(index2+1,index3));            int score2 = Integer.parseInt(str.substring(index3+1,str.length()));            if (score1==score2){                teamMap.get(team2).nofailnoSuccess().hit(score2);                teamMap.get(team1).nofailnoSuccess().hit(score1);            }else if (score1>score2){                teamMap.get(team1).success().hit(score1).pure(score1-score2);                teamMap.get(team2).hit(score2).pure(score1-score2);            }else {                teamMap.get(team2).success().hit(score1).pure(score2-score1);                teamMap.get(team1).hit(score2).pure(score2-score1);            }        }        Collections.sort(teams);        int i=0;        List<String> finalGame = new ArrayList<>();        for (Team team : teams){            i++;            finalGame.add(team.name);            if (i==n/2){                break;            }        }        Collections.sort(finalGame);        for (String name : finalGame){            System.out.println(name);        }    }    static class Team implements Comparable<Team> {        private String name;        private int score;  // 总积分        private int hit;    // 进球数        private int pure;   // 净胜球数        public Team(String name) {            this.name = name;            this.score = 0;        }        public Team hit(int n){            this.hit+=n;            return this;        }        public Team pure(int n){            this.pure += n;            return this;        }        public Team success(){            this.score+=3;            return this;        }        public Team nofailnoSuccess(){            this.score+=1;            return this;        }        @Override        public int compareTo(Team a){            if (this.score!=a.score){                return -this.score + a.score;            }else if(this.pure != a.pure){                return -this.pure+a.pure;            }else {                return -this.hit+a.hit;            }        }    }}