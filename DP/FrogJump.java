/**
* A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
* 
* Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
* 
* If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
* 
* Note:
* 
* The number of stones is ≥ 2 and is < 1,100.
* Each stone's position will be a non-negative integer < 231.
* The first stone's position is always 0.
* Example 1:
* 
* [0,1,3,5,6,8,12,17]
* 
* There are a total of 8 stones.
* The first stone at the 0th unit, second stone at the 1st unit,
* third stone at the 3rd unit, and so on...
* The last stone at the 17th unit.
* 
* Return true. The frog can jump to the last stone by jumping 
* 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
* 2 units to the 4th stone, then 3 units to the 6th stone, 
* 4 units to the 7th stone, and 5 units to the 8th stone.
* Example 2:
* 
* [0,1,2,3,4,8,9,11]
* 
* Return false. There is no way to jump to the last stone as 
* the gap between the 5th and 6th stone is too large.
*/

/*
solution 2
时间O(n^2) 空间O(n)
思路：我为人人型动态规划 
维护一个map,键为stones[i],值为在stones[i]可能跳的步数 
*/

public class FrogJump {
    public boolean canCross(int[] stones) {
        if(stones == null || stones.length < 1) return false;
        if(stones[1] != 1) return false;
        int len = stones.length;
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < len; i++) {
            map.put(stones[i], new HashSet<Integer>());
            if(i == 0)
                map.get(stones[i]).add(1);
        }
        
        for(int i = 0; i < len; i++) {
            int unit = stones[i];
            for(int step : map.get(unit)){
                int pos = unit + step;
                
                if(pos == stones[len - 1])
                    return true;
                
                Set<Integer> set = map.get(pos);
                if(set != null) {
                    set.add(step);
                    if(step - 1 > 0)
                        set.add(step - 1);
                    set.add(step + 1);
                }
            }
        }
        return false;
    }
}

/*
solution 1 
复杂度
时间O(n^2) 空间O(n^2)
思路：
这种求可能性的问题 首先想能否用动态规划 考虑最基本的暴力解法是什么 然后怎么去利用dp去优化 
在每个石头上有三个可能跳的距离 然后有n个石头 理论上有3^n种可能性(乘法原理) 但并非每个石头都要去跳一下 
对于某个要经过的石头 也有三种可能到达的方式 动归优化的空间就有了
维护一个二维数组canJump,canJump[i][j]表示从某个石头跳j步到石头i的最远的可能性 初始化数组都为-1，代表未知可否
然后递归滴去访问每一种可能性 由于我们在深搜的过程中更新canJump 所以等于是记忆化搜索 起到了剪枝的效果  
*/
public class FrogJump {
    public boolean canCross(int[] stones) {
        if(stones == null || stones.length < 1) return false;
        if(stones[1] != 1) return false;
        int len = stones.length;
        int[][] canJump = new int[len][len];
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                canJump[i][j] = -1;
            }
        }
        return jumpable(canJump, stones, 0, 0, len);
    }
    //用j步跳到第i个石头
    private boolean jumpable(int[][] canJump, int[] stones, int i, int j, int len) {
        //来过这一步了。
        if(canJump[i][j] != -1) return canJump[i][j] == 1;
        //跳到最后了
        if(i == len - 1) {
            canJump[i][j] = 1;
            return true;
        }
        for(int k = i + 1; k < len; k++) {
            //距离太近
            if(stones[k] < stones[i] + j - 1)
                continue;
            //距离太远
            else if(stones[k] > stones[i] + j + 1) {
                //这步可以跳到，但是后续跳不到
                canJump[i][j] = 0;
                continue;
            }else {
                //递归检查。如果之后跳到k以后能一直跳到最后，那么才将canJump[i][j] = 1, 并且return true
                //否则就继续for循环
                if(jumpable(canJump, stones, k, stones[k] - stones[i], len)) {
                    canJump[i][j] = 1;
                    return true;
                }
            }
        }
        //i这个石头可以跳j步跳到，但是后续跳不到
        canJump[i][j] = 0;
        return false;
    }
}
