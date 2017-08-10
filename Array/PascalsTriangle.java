/**
* Given numRows, generate the first numRows of Pascal's triangle.
* 
* For example, given numRows = 5,
* Return
* 
* [
*      [1],
*     [1,1],
*    [1,2,1],
*   [1,3,3,1],
*  [1,4,6,4,1]
* ]
*/

/*
复杂度
时间：O(n^2) 空间:O(1)
思路：
每层保存前一行的指针，然后当前行数据根据上一行来得到，每个元素就是上一行两个相邻元素相加（第一个和最后一个元素是1）
算法时间复杂度应该是O(1+2+3+...+n)=O(n^2)，空间上只需要二维数组来存储结果，不需要额外空间
*/

public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(numRows <= 0) return res;
        List<Integer> row = new ArrayList<Integer>();
        
        for(int i=0; i<numRows; i++){ 
            row.add(0, 1); // first, increase the new row size by 1.
            for(int j=1; j<row.size()-1; j++){
                row.set(j, row.get(j)+row.get(j+1));
            }
            res.add(new ArrayList<Integer>(row));
        }
        return res;
    }
}