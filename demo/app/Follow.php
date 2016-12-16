<?php
/**
 * @author: David Bezalel Laoli (david.laoly@gmail.com)
 * 1 Des 2016
 */

namespace App;
use DB;

use Illuminate\Database\Eloquent\Model;

class Follow extends Model {

    protected $table = 'follows';

    protected $fillable = array(
        'user_id', 'following_id'
    );

    public function detect ($userid, $followingid) {
        $_where = array(
            ['user_id', '=',  $userid],
            ['following_id', '=', $followingid]
        );
        $_found = $this->find($_where);
        return $_found;
    }

    public function remove ($userid, $followingid) {
        $_where = array(
            ['user_id', '=',  $userid],
            ['following_id', '=', $followingid]
        );

        $this->delete($_where);

    }

    public function getfollowing ($id) {
        $_where = array(
            ['user_id', '=', $id]
        );

        return $this->find($_where, true);
    }

    public function getfollowers ($id) {
        $_where = array(
            ['following_id', '=', $id]
        );

        return $this->find($_where, true);
    }
}